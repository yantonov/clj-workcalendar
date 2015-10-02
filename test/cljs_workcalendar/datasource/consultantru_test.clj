(ns cljs-workcalendar.datasource.consultantru-test
  (:require [cljs-workcalendar.workdayitem])
  (:import  [cljs_workcalendar.workdayitem WorkDayItem])
  (:require [cljs-workcalendar.datasource.consultantru :as impl])
  (:import  [cljs_workcalendar.datasource.consultantru ConsultantRuWorkCalendarSource])
  (:require [cljs-workcalendar.datasource.workcalendarsource :as source])
  (:require [clojure.test :refer :all])
  (:require [cljs-workcalendar.datasource.calendar-test-data :as test-data]))

(defn- special-days-for-year [year]
  (let [all-items (source/get-work-calendar (ConsultantRuWorkCalendarSource.))]
    (vec (sort-by (fn [x] (+ (* 31 (get x 0))
                             (get x 1)))
                  (map #(vector (:month %)
                                (:day %)
                                (:type %))
                       (filter #(= (:year %) year) all-items))))))

(deftest calendar-2016
  (is (= (test-data/calendar-2016)
         (special-days-for-year 2016))))

(deftest calendar-2015
  (is (= (test-data/calendar-2015)
         (special-days-for-year 2015))))

(deftest calendar-2014
  (is (= (test-data/calendar-2014)
         (special-days-for-year 2014))))

(deftest calendar-2013
  (is (= (test-data/calendar-2013)
         (special-days-for-year 2013))))

(deftest calendar-2012
  (is (= (test-data/calendar-2012)
         (special-days-for-year 2012))))
