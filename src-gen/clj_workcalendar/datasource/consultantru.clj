(ns clj-workcalendar.datasource.consultantru
  (:require [clj-workcalendar.workdayitem])
  (:import  [clj_workcalendar.workdayitem WorkDayItem])
  (:require [clj-workcalendar.datasource.workcalendarsource :as source])
  (:require [net.cgrand.enlive-html :as html])
  (:require [clj-http.client :as http]))

(defrecord ConsultantRuWorkCalendarSource [])

(defn create-source []
  (ConsultantRuWorkCalendarSource.))

(defn- day-placeholder? [html-element]
  (.contains (:class (:attrs html-element)) "inactively"))

(defn- weekend? [html-element]
  (.contains (:class (:attrs html-element)) "weekend"))

(defn- day [html-element]
  (Integer/parseInt (.trim (first (:content html-element)))))

(defn- extract-non-working-days [calendar-element year month]
  (remove
   nil?
   (map (fn [day-element day-of-week]
          (cond
            (day-placeholder? day-element)
            nil

            (and (>= day-of-week 1)
                 (<= day-of-week 5)
                 (weekend? day-element))
            (WorkDayItem. year month (day day-element) :holiday)

            (and (>= day-of-week 6)
                 (<= day-of-week 7)
                 (not (weekend? day-element)))
            (WorkDayItem. year month (day day-element) :workday)

            true
            nil))
        (html/select calendar-element [:table :tbody :tr :td])
        (apply concat (repeatedly #(range 1 8))))))

(defn- get-work-calendar-for-year [year url]
  (let [rawPage (try
                  (:body (http/get url))
                  (catch Exception e ""))
        page (html/html-resource (java.io.StringReader. rawPage))
        month-calendars (html/select page [:.cal])]
    (mapcat (fn [calendar month-index]
              (extract-non-working-days calendar year month-index))
            month-calendars
            (iterate inc 1))))

(extend-protocol source/WorkCalendarSource
  ConsultantRuWorkCalendarSource
  (get-work-calendar [this]
    (mapcat
     #(get-work-calendar-for-year %
                                  (str "http://www.consultant.ru/law/ref/calendar/proizvodstvennye/" %))
     (remove #(= 2020 %) (range 2012 2024)))))
