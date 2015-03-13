(ns cljs-workcalendar.app
  (:require [cljs-workcalendar.datasource.workcalendarsource :as source])
  (:require [cljs-workcalendar.datasource.consultantru])
  (:import  [cljs_workcalendar.datasource.consultantru ConsultantRuWorkCalendarSource])
  (:require [cljs-workcalendar.format.formatinterface :as fmt])
  (:require [cljs-workcalendar.format.plaintextformatter])
  (:import [cljs_workcalendar.format.plaintextformatter PlainTextWorkCalendarFormatter])
  (:require [clojure.java.io :as io]))

(defn -main [& args]
  (let [source (ConsultantRuWorkCalendarSource.)
        fmt (PlainTextWorkCalendarFormatter.)]
    (with-open [writer (io/writer "work-calendar.txt")]
      (doseq [line (fmt/format-work-calendar
                    fmt
                    (sort-by (fn [item]
                               (format "%04d.%02d.%02d"
                                       (:year item)
                                       (:month item)
                                       (:day item)))
                             (source/get-work-calendar source)))]
        (.write writer line)))))
