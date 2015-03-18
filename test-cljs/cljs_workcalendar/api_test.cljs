(ns cljs-workcalendar.api-test
  (:require [cljs.test :refer-macros [deftest testing is are]]
            [cljs-workcalendar.api :as cs]))

(defn- is-workday? [service year month day]
  (.is-workday service (js/Date. year (dec month) day)))

(deftest is-workday-test
  (let [service (cs/createWorkCalendarService)]

    (are [year month day workday?]
      (= workday? (.is-workday service (js/Date. year (dec month) day)))
      2012 1 1 false
      2012 1 2 false
      2012 1 3 false
      2012 1 10 true
      2012 1 13 true
      2012 2 23 false
      2013 3 11 true
      2012 6 9 true)))
