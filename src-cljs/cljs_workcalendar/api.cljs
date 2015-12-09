(ns cljs-workcalendar.api
  (:require [cljs-workcalendar.calendar-data :as data]))

(defn- date2vec [date]
  (vector (.getFullYear date)
          (inc (.getMonth date))
          (.getDate date)))

(defn- add-days [date count]
  (js/Date. (+ (.getTime date)
               (* 24 60 60 1000 count))))

(defn- skip-time [date]
  (js/Date. (.getFullYear date)
            (.getMonth date)
            (.getDate date)))

(defn- day-of-week [date]
  (.getDay date))

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
    (let [day (day-of-week date)]
      (or (= day 0)
          (= day 6))))

  (is-special-working-day [this date]
    (= :workday (get work-calendar-map (date2vec date) :not-found)))

  (is-holiday [this date]
    (= :holiday (get work-calendar-map (date2vec date) :not-found)))

  (move-to-workday [this date]
    (.get-nearest-workday this date false))

  (move-to-workday-backwards [this date]
    (.get-nearest-workday this date true))

  (get-nearest-workday [this date move-backwards]

    (let [direction (if move-backwards -1 1)]
      (first
       (filter (fn [d]
                 (.is-workday this d))
               (iterate (fn [d] (add-days d direction)) date)))))

  (add-work-days [this date count]
    (if (or (zero? count)
            (nil? count))
      (throw (js/Error. "number of days is equal to zero"))
      (let [n (Math/abs count)
            direction (if (> count 0) false true)
            direction-int (if (> count 0) 1 -1)]
        (first
         (drop n
               (filter (fn [d] (.is-workday this d))
                       (iterate (fn [d] (add-days d direction-int)) date)))))))

  (work-day-count [this start-date end-date]
    (if (> (.getTime start-date)
           (.getTime end-date))
      (throw (js/Error "invalid period"))
      (let [from (skip-time start-date)
            to (.getTime (skip-time end-date))]
        (count
         (filter (fn [d] (.is-workday this d))
                 (take-while (fn [d] (<= (.getTime d)
                                         to))
                             (iterate (fn [d] (add-days d 1)) from))))))))

(defn createWorkCalendarService []
  ;; create map from [y m d] -> day type (:holiday :workday)
  (let [m (apply hash-map
                 (mapcat (fn [[year month day type]]
                           (vector [year month day] type))
                         data/work-calendar))]
    (WorkCalendar. m)))
