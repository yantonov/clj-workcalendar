(ns cljs-workcalendar.app
  (:require [cljs-workcalendar.datasource.workcalendarsource :as source])

  (:require [cljs-workcalendar.datasource.consultantru])
  (:import  [cljs_workcalendar.datasource.consultantru ConsultantRuWorkCalendarSource])

  (:require [cljs-workcalendar.format.formatinterface :as fmt])
  (:require [cljs-workcalendar.format.plaintextformatter])
  (:require [cljs-workcalendar.format.cljsformatter])
  (:import [cljs_workcalendar.format.plaintextformatter PlainTextWorkCalendarFormatter])
  (:import [cljs_workcalendar.format.cljsformatter ClojureScriptWorkCalendarFormatter])
  (:require [clojure.java.io :as io]))

(defn- serialize-calendar [work-calendar formatter file-name]
  (with-open [writer (io/writer file-name)]
    (doseq [line (fmt/format-work-calendar
                  formatter
                  work-calendar)]
      (.write writer (str line "\n")))))

(defn -main [& args]
  (let [calendar (sort-by (fn [item]
                            (format "%04d.%02d.%02d"
                                    (:year item)
                                    (:month item)
                                    (:day item)))
                          (source/get-work-calendar
                           (ConsultantRuWorkCalendarSource.)))]
    (do
      (serialize-calendar calendar
                          (PlainTextWorkCalendarFormatter.)
                          "deploy/data/work-calendar.txt")
      (serialize-calendar calendar
                          (ClojureScriptWorkCalendarFormatter.)
                          "src-cljs/cljs_workcalendar/calendar_data.cljs"))))
