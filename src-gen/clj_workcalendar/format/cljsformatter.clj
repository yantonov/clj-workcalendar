(ns clj-workcalendar.format.cljsformatter
  (:require [clj-workcalendar.format.formatinterface :as f])
  (:require [clojure.java.io :as io]))

(defrecord ClojureScriptWorkCalendarFormatter []
  f/ICanFormatWorkCalendar
  (format-work-calendar [this work-calendar]
    (concat
     ["(ns clj-workcalendar.calendar-data)"
      ""
      "(def work-calendar"
      "["]
     (map (fn [item]
            (format "[%d %d %d %s]"
                    (:year item)
                    (:month item)
                    (:day item)
                    (:type item)))
          work-calendar)
     ["])"])))

(defn create []
  (ClojureScriptWorkCalendarFormatter.))
