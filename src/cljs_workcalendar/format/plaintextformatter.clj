(ns cljs-workcalendar.format.plaintextformatter
  (:require [cljs-workcalendar.format.formatinterface :as f])
  (:require [clojure.java.io :as io]))

(defrecord PlainTextWorkCalendarFormatter []
  f/ICanFormatWorkCalendar
  (format-work-calendar [this work-calendar]
    (with-open [writer (io/writer "test.txt")]
      (doseq [item work-calendar]
        (.write writer (format "%04d.%02d.%02d:%d\n"
                               (:year item)
                               (:month item)
                               (:day item)
                               (let [t (:type item)]
                                 (if (= t :holiday) 0 1))))))))
