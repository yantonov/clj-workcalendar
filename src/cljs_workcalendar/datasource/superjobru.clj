(ns cljs-workcalendar.datasource.superjobru
  (:require [cljs-workcalendar.workdayitem])
  (:import  [cljs_workcalendar.workdayitem WorkDayItem])
  (:require [cljs-workcalendar.datasource.workcalendarsource :as source])
  (:require [net.cgrand.enlive-html :as html])
  (:require [clj-http.client :as http]))

(defn- prev-month-day? [html-element]
  (.contains (:class (:attrs html-element)) "h_color_gray"))

(defn- weekend? [html-element]
  (let [c (:class (:attrs html-element))]
    (and (not (nil? c))
         (.contains c "MonthsList_holiday"))))

(defn- day [html-element]
  (Integer/parseInt (first (:content html-element))))

(defn- extract-non-working-days [calendar-element year month]
  (filter #(not (nil? %))
          (map (fn [day-element day-of-week]
                 (cond
                   (prev-month-day? day-element)
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
               (html/select calendar-element [:.MonthsList_date])
               (apply concat (repeatedly #(range 1 8))))))

(defn- get-work-calendar-for-year [year url]
  (let [rawPage (try
                  (:body (http/get url))
                  (catch Exception e ""))
        page (html/html-resource (java.io.StringReader. rawPage))
        month-calendars (html/select page [:.MonthsList_grid])]
    (apply concat (map (fn [calendar month-index]
                         (extract-non-working-days calendar year month-index))
                       month-calendars
                       (iterate inc 1)))))


(defrecord SuperjobRuWorkCalendarSource [])

(defn create-source []
  (SuperjobRuWorkCalendarSource.))

(extend-protocol source/WorkCalendarSource
  SuperjobRuWorkCalendarSource
  (get-work-calendar [this]
    (concat (get-work-calendar-for-year 2016
                                        "http://www.superjob.ru/proizvodstvennyj_kalendar/2016/")
            (get-work-calendar-for-year 2015
                                        "http://www.superjob.ru/proizvodstvennyj_kalendar/2015/")
            (get-work-calendar-for-year 2014
                                        "http://www.superjob.ru/proizvodstvennyj_kalendar/2014/")
            (get-work-calendar-for-year 2013
                                        "http://www.superjob.ru/proizvodstvennyj_kalendar/2013/"))))
