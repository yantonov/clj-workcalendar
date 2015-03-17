(ns cljs-workcalendar.api-test
  (:require [cljs.test :refer-macros [deftest testing is]]
            [cljs-workcalendar.api :as cs]))

(deftest simple
  (let [service (cs/createWorkCalendarService)]
    (is (not (.is-workday service (js/Date 2012 (dec 1) 2))))))
