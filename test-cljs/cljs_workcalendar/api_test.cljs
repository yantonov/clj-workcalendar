(ns cljs-workcalendar.api-test
  (:require [cljs.test :refer-macros [deftest testing is are]]
            [cljs-workcalendar.api :as cs]))

(defn- is-workday? [service year month day]
  (.is-workday service (js/Date. year (dec month) day)))

(deftest is-workday-test
  (let [service (cs/createWorkCalendarService)]

    (are [year month day workday?]
      (= workday? (.is-workday service (js/Date. year (dec month) day)))
      2012 1 1 false ; holiday
      2012 1 2 false ; holiday
      2012 1 3 false ; holiday
      2012 1 10 true ; workday
      2012 1 13 true ; workday
      2012 2 23 false ; holiday
      2012 3 8 false ; holiday
      2012 3 9 false ; holiday
      2012 3 10 false ; weekend
      2012 3 11 true ; special workday
      2012 6 9 true ; special workday
      2012 6 16 false ; weekend
      2012 6 17 false ;weekend
      )))
