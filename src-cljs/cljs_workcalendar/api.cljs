(ns cljs-workcalendar.api
  (:require [cljs-workcalendar.calendar-data :as data]))

(enable-console-print!)

(defn createWorkCalendarService []
  ;; create map from [y m d] -> day type (:holiday :workday)
  (let [m (apply hash-map
                 (mapcat (fn [[year month day type]]
                           [[year month day] type])
                         data/work-calendar))]
    (WorkCalendar. m)))


(defn- date2vec [date]
  (vector (.getFullYear date)
          (inc (.getMonth date))
          (.getDate date)))

(deftype WorkCalendar [work-calendar-map]
  Object
  (is-workday [this date]
    (not (.is-non-working-day this date)))

  (is-non-working-day [this date]
    (or (and (.is-weekend this date)
             (not (.is-special-working-day this date)))
        (and (not (.is-weekend this date))
             (.is-holiday this date))))

  (is-weekend [this date]
    (let [day (.getDay date)]
      (or (= day 0)
          (= day 6))))

  (is-special-working-day [this date]
    (= :workday (get work-calendar-map (date2vec date) :not-found)))

  (is-holiday [this date]
    (= :holiday (get work-calendar-map (date2vec date) :not-found))))
