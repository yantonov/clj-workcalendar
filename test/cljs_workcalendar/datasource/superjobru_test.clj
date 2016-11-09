(ns cljs-workcalendar.datasource.superjobru-test
  (:require [cljs-workcalendar.workdayitem])
  (:import  [cljs_workcalendar.workdayitem WorkDayItem])
  (:require [cljs-workcalendar.datasource.superjobru :as impl])
  (:import  [cljs_workcalendar.datasource.superjobru SuperjobRuWorkCalendarSource])
  (:require [clojure.test :refer :all])
  (:require [cljs-workcalendar.datasource.calendar-test-data :as test-data])
  (:require [cljs-workcalendar.datasource.source-test-util :as util]))

(defn- special-days-for-year [year]
  (util/special-days-for-year year (SuperjobRuWorkCalendarSource.)))

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
