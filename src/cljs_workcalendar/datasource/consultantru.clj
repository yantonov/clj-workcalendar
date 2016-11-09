(ns cljs-workcalendar.datasource.consultantru
  (:require [cljs-workcalendar.workdayitem])
  (:import  [cljs_workcalendar.workdayitem WorkDayItem])
  (:require [cljs-workcalendar.datasource.workcalendarsource :as source])
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
  (filter #(not (nil? %))
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
        month-calendars (html/select page [:.month-block])]
    (apply concat (map (fn [calendar month-index]
                         (extract-non-working-days calendar year month-index))
                       month-calendars
                       (iterate inc 1)))))

(extend-protocol source/WorkCalendarSource
  ConsultantRuWorkCalendarSource
  (get-work-calendar [this]
    (concat (get-work-calendar-for-year 2016
                                        "http://www.consultant.ru/law/ref/calendar/proizvodstvennye/")
            (get-work-calendar-for-year 2015
                                        "http://www.consultant.ru/law/ref/calendar/proizvodstvennye/2015/")
            (get-work-calendar-for-year 2014
                                        "http://www.consultant.ru/law/ref/calendar/proizvodstvennye/2014/")
            (get-work-calendar-for-year 2013
                                        "http://www.consultant.ru/law/ref/calendar/proizvodstvennye/2013/")
            (get-work-calendar-for-year 2012
                                        "http://www.consultant.ru/law/ref/calendar/proizvodstvennye/2012/"))))
