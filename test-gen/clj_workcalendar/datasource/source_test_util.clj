(ns clj-workcalendar.datasource.source-test-util)

(defn special-days-for-year [year all-items]
  (vec (sort-by (fn [x] (+ (* 31 (get x 0))
                           (get x 1)))
                (map #(vector (:month %)
                              (:day %)
                              (:type %))
                     (filter #(= (:year %) year) all-items)))))

