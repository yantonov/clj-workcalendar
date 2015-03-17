(ns test-runner
  (:require [cljs.test :refer-macros [run-tests]]
            [cljs-workcalendar.api-test]))

(enable-console-print!)

(defn runner []
  (if (cljs.test/successful?
       (run-tests
        'cljs-workcalendar.api-test))
    0
    1))
