(ns cljs-workcalendar.datasource.source-test-util
  (:require [cljs-workcalendar.datasource.workcalendarsource :as source]))

(defn special-days-for-year [year datasource]
  (let [all-items (source/get-work-calendar datasource)]
    (vec (sort-by (fn [x] (+ (* 31 (get x 0))
                             (get x 1)))
                  (map #(vector (:month %)
                                (:day %)
                                (:type %))
                       (filter #(= (:year %) year) all-items))))))

