(ns clj-workcalendar.datasource.consultantru-test
  (:require [clj-workcalendar.workdayitem])
  (:import  [clj_workcalendar.workdayitem WorkDayItem])
  (:require [clj-workcalendar.datasource.consultantru :as impl])
  (:require [clojure.test :refer :all])
  (:require [clj-workcalendar.datasource.calendar-test-data :as test-data])
  (:require [clj-workcalendar.datasource.source-test-util :as util])
  (:require [clj-workcalendar.datasource.workcalendarsource :as source]))

(deftest calendar
  (let [source-data (source/get-work-calendar (impl/create-source))]
    (doall (for [year (range 2012 2024)]
             (let []
               ;; this is only for debug (add year to the assertion statement)
               ;; (println year)
               (is (= (get (test-data/calendar) year [])
                      (util/special-days-for-year year source-data))))))))
