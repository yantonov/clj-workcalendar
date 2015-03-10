(ns cljs-workcalendar.spider.consultantru-test
  (:require [cljs-workcalendar.workdayitem])
  (:import  [cljs_workcalendar.workdayitem WorkDayItem])
  (:require [cljs-workcalendar.spider.consultantru :as impl])
  (:import  [cljs_workcalendar.spider.consultantru ConsultantRuWorkCalendarSource])
  (:require [cljs-workcalendar.spider.workcalendarsource :as source])
  (:require [clojure.test :refer :all]))

(deftest calendar-2015-special-day-count
  (is (= 14 (count (filter #(= 2015 (:year %))
                           (source/get-work-calendar (ConsultantRuWorkCalendarSource.)))))))

(deftest calendar-2015
  (let [items (source/get-work-calendar (ConsultantRuWorkCalendarSource.))]
    (are [m d type]
      (= 1
         (count (filter #(and
                          (= (:year %) 2015)
                          (= (:month %) m)
                          (= (:day %) d)
                          (= (:type %) type)
                          )
                        items)))
      1 1  :holiday
      1 2  :holiday
      1 5  :holiday
      1 6  :holiday
      1 7  :holiday
      1 8  :holiday
      1 9  :holiday
      2 23 :holiday
      3 9  :holiday
      5 1  :holiday
      5 4  :holiday
      5 11 :holiday
      6 12 :holiday
      11 4 :holiday)))

(deftest calendar-2014-special-day-count
  (is (= 14 (count (filter #(= 2014 (:year %))
                           (source/get-work-calendar (ConsultantRuWorkCalendarSource.)))))))

(deftest calendar-2014
  (let [items (source/get-work-calendar (ConsultantRuWorkCalendarSource.))]
    (are [m d type]
      (= 1
         (count (filter #(and
                          (= (:year %) 2014)
                          (= (:month %) m)
                          (= (:day %) d)
                          (= (:type %) type)
                          )
                        items)))
      1 1  :holiday
      1 2  :holiday
      1 3  :holiday
      1 6  :holiday
      1 7  :holiday
      1 8  :holiday
      3 10  :holiday
      5 1  :holiday
      5 2  :holiday
      5 9  :holiday
      6 12  :holiday
      6 13  :holiday
      11 3  :holiday
      11 4  :holiday
)))
