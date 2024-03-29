(ns clj-workcalendar.app
  (:require [clj-workcalendar.datasource.workcalendarsource :as source])
  (:require [clj-workcalendar.datasource.consultantru :as cns])
  (:require [clj-workcalendar.format.formatinterface :as fmt])
  (:require [clj-workcalendar.format.plaintextformatter])
  (:require [clj-workcalendar.format.cljsformatter])
  (:require [clj-workcalendar.format.plaintextformatter :as ptf])
  (:require [clj-workcalendar.format.cljsformatter :as cljf])
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
                          (distinct
                           (concat (source/get-work-calendar (cns/create-source)))))]
    (serialize-calendar calendar
                        (ptf/create)
                        "deploy/data/work-calendar.txt")
    (serialize-calendar calendar
                        (cljf/create)
                        "src/clj_workcalendar/calendar_data.cljc")))
