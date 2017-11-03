(ns test-runner
  (:require [cljs.test :refer-macros [run-tests]]
            [clj-workcalendar.api-test]))

(enable-console-print!)

(defn runner []
  (if (cljs.test/successful?
       (run-tests
        'clj-workcalendar.api-test))
    0
    1))
