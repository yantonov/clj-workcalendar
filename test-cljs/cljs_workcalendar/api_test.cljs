(ns cljs-workcalendar.api-test
  (:require [cljs.test :refer-macros [deftest testing is are]]
            [cljs-workcalendar.api :as cs]))

(deftest is-workday-test
  (are [year month day workday?]
      (= workday? (cs/is-workday (js/Date. year (dec month) day)))
    2012 1 1  false ; holiday
    2012 1 2  false ; holiday
    2012 1 3  false ; holiday
    2012 1 10 true  ; workday
    2012 1 13 true  ; workday
    2012 2 23 false ; holiday
    2012 3 8  false ; holiday
    2012 3 9  false ; holiday
    2012 3 10 false ; weekend
    2012 3 11 true  ; special workday
    2012 6 9  true  ; special workday
    2012 6 16 false ; weekend
    2012 6 17 false ; weekend
    ))

(defn- date2vec [date]
  (vector (.getFullYear date)
          (inc (.getMonth date))
          (.getDate date)))

(deftest move-to-workday-test
  (are [year month day
        to-year to-month to-day]
      (= [to-year to-month to-day]
         (date2vec (cs/move-to-workday (js/Date. year (dec month) day))))
    2012 1  1  2012 1 10 ; new year holidays
    2012 1  9  2012 1 10 ; to workday
    2012 1 10  2012 1 10 ; workday - stay here
    2012 1 14  2012 1 16 ; 2 day weekend weekend
    2012 1 15  2012 1 16 ; 1 day weekend
    2012 3 8   2012 3 11 ; to special workday
    ))

(deftest move-to-workday-backwards-test
  (are [year month day
        to-year to-month to-day]
      (= [to-year to-month to-day]
         (date2vec (cs/move-to-workday-backwards (js/Date. year (dec month) day))))
    2012 1  9  2011 12 30 ; new year holidays
    2012 1  1  2011 12 30 ; 1 day holiday
    2012 1 10  2012 1 10 ; workday - stay here
    2012 1 15  2012 1 13 ; 2 day weekend weekend
    2012 1 14  2012 1 13 ; 1 day weekend
    2012 3 11  2012 3 11 ; special workday - stay here
    2012 6 12  2012 6 9  ; to special workday
    2012 6 11  2012 6 9  ; to special workday
    2012 6 10  2012 6 9  ; to special workday
    ))

(deftest add-days-test
  (are [year month day
        add-count
        to-year to-month to-day]
      (= [to-year to-month to-day]
         (date2vec (cs/add-work-days (js/Date. year (dec month) day) add-count)))
    2012 1 10  1 2012 1 11
    2012 1 11 -1 2012 1 10
    2012 1 10  2 2012 1 12
    2012 1 9   2 2012 1 12
    2012 1  1  2 2012 1 12
    2012 1 12  -3 2011 12 30))

(deftest work-day-count-test
  (are [from-year from-month from-day
        to-year to-month to-day
        expected-length]
      (= expected-length (cs/work-day-count (js/Date. from-year
                                                      (dec from-month)
                                                      from-day)
                                            (js/Date. to-year
                                                      (dec to-month)
                                                      to-day)))
    2012 1 1 2012 1 10 1
    2012 1 1 2012 1 31 16
    2012 5 1 2012 5 31 21
    2012 6 1 2012 6 30 20))
