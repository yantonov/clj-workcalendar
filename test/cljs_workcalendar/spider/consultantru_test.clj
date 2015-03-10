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
    (are [y m d type]
      (= 1
         (count (filter #(and
                          (= (:year %) y)
                          (= (:month %) m)
                          (= (:day %) d)
                          (= (:type %) type)
                          )
                        items)))
      2015 1 1  :holiday
      2015 1 2  :holiday
      2015 1 5  :holiday
      2015 1 6  :holiday
      2015 1 7  :holiday
      2015 1 8  :holiday
      2015 1 9  :holiday
      2015 2 23 :holiday
      2015 3 9  :holiday
      2015 5 1  :holiday
      2015 5 4  :holiday
      2015 5 11 :holiday
      2015 6 12 :holiday
      2015 11 4 :holiday)))
