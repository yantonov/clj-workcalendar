(ns cljs-workcalendar.datasource.consultantru-test
  (:require [cljs-workcalendar.workdayitem])
  (:import  [cljs_workcalendar.workdayitem WorkDayItem])
  (:require [cljs-workcalendar.datasource.consultantru :as impl])
  (:require [clojure.test :refer :all])
  (:require [cljs-workcalendar.datasource.calendar-test-data :as test-data])
  (:require [cljs-workcalendar.datasource.source-test-util :as util]))

(defn- special-days-for-year [year]
  (util/special-days-for-year year (impl/create-source)))

(deftest calendar-2018
  (is (= (test-data/calendar-2018)
         (special-days-for-year 2018))))

(deftest calendar-2017
  (is (= (test-data/calendar-2017)
         (special-days-for-year 2017))))

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
