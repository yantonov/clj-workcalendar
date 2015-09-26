(ns cljs-workcalendar.datasource.superjobru-test
  (:require [cljs-workcalendar.workdayitem])
  (:import  [cljs_workcalendar.workdayitem WorkDayItem])
  (:require [cljs-workcalendar.datasource.superjobru :as impl])
  (:import  [cljs_workcalendar.datasource.superjobru SuperjobRuWorkCalendarSource])
  (:require [cljs-workcalendar.datasource.workcalendarsource :as source])
  (:require [clojure.test :refer :all]))

(defn- special-days-for-year [year]
  (let [all-items (source/get-work-calendar (SuperjobRuWorkCalendarSource.))]
    (sort-by (fn [x] (+ (* 31 (get x 0))
                        (get x 1)))
             (map #(vector (:month %)
                           (:day %)
                           (:type %))
                  (filter #(= (:year %) year) all-items)))))

(deftest calendar-2016
  (is (= [[1 1  :holiday]
          [1 4  :holiday]
          [1 5  :holiday]
          [1 6  :holiday]
          [1 7  :holiday]
          [1 8  :holiday]
          [2 20 :workday]
          [2 22 :holiday]
          [2 23 :holiday]
          [3 7  :holiday]
          [3 8  :holiday]
          [5 2  :holiday]
          [5 3  :holiday]
          [5 9  :holiday]
          [6 13 :holiday]
          [11 4 :holiday]]
         (special-days-for-year 2016))))

(deftest calendar-2015
  (is (= [[1 1  :holiday]
          [1 2  :holiday]
          [1 5  :holiday]
          [1 6  :holiday]
          [1 7  :holiday]
          [1 8  :holiday]
          [1 9  :holiday]
          [2 23 :holiday]
          [3 9  :holiday]
          [5 1  :holiday]
          [5 4  :holiday]
          [5 11 :holiday]
          [6 12 :holiday]
          [11 4 :holiday]]
         (special-days-for-year 2015))))

(deftest calendar-2014
  (is (=[ [1 1  :holiday]
          [1 2  :holiday]
          [1 3  :holiday]
          [1 6  :holiday]
          [1 7  :holiday]
          [1 8  :holiday]
          [3 10 :holiday]
          [5 1  :holiday]
          [5 2  :holiday]
          [5 9  :holiday]
          [6 12 :holiday]
          [6 13 :holiday]
          [11 3 :holiday]
          [11 4 :holiday]]
        (special-days-for-year 2014))))

(deftest calendar-2013
  (is (=[[1 1  :holiday]
         [1 2  :holiday]
         [1 3  :holiday]
         [1 4  :holiday]
         [1 7  :holiday]
         [1 8  :holiday]
         [3 8  :holiday]
         [5 1  :holiday]
         [5 2  :holiday]
         [5 3  :holiday]
         [5 9  :holiday]
         [5 10  :holiday]
         [6 12  :holiday]
         [11 4  :holiday]]
        (special-days-for-year 2013))))

(deftest calendar-2012
  (is (= [[1 2   :holiday]
          [1 3   :holiday]
          [1 4   :holiday]
          [1 5   :holiday]
          [1 6   :holiday]
          [1 9   :holiday]
          [2 23  :holiday]
          [3 8   :holiday]
          [3 9   :holiday]
          [3 11  :workday]
          [4 28  :workday]
          [4 30  :holiday]
          [5 1   :holiday]
          [5 5   :workday]
          [5 7   :holiday]
          [5 8   :holiday]
          [5 9   :holiday]
          [5 12  :workday]
          [6 9   :workday]
          [6 11  :holiday]
          [6 12  :holiday]
          [11 5  :holiday]
          [12 29 :workday]
          [12 31 :holiday]]
         (special-days-for-year 2012))))

