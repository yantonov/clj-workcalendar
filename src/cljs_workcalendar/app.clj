(ns cljs-workcalendar.app
  (:require [cljs-workcalendar.datasource.workcalendarsource :as source])
  (:require [cljs-workcalendar.datasource.consultantru :as cns])
  (:require [cljs-workcalendar.datasource.superjobru :as sj])
  (:require [cljs-workcalendar.format.formatinterface :as fmt])
  (:require [cljs-workcalendar.format.plaintextformatter])
  (:require [cljs-workcalendar.format.cljsformatter])
  (:require [cljs-workcalendar.format.plaintextformatter :as ptf])
  (:require [cljs-workcalendar.format.cljsformatter :as cljf])
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
                           (concat (source/get-work-calendar (cns/create-source))
                                   (source/get-work-calendar (sj/create-source)))))]
    (do
      (serialize-calendar calendar
                          (ptf/create)
                          "deploy/data/work-calendar.txt")
      (serialize-calendar calendar
                          (cljf/create)
                          "src-cljs/cljs_workcalendar/calendar_data.cljs"))))
