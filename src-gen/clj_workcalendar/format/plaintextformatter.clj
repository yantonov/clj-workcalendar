(ns clj-workcalendar.format.plaintextformatter
  (:require [clj-workcalendar.format.formatinterface :as f])
  (:require [clojure.java.io :as io]))

(defrecord PlainTextWorkCalendarFormatter []
  f/ICanFormatWorkCalendar
  (format-work-calendar [this work-calendar]
    (map (fn [item]
           (format "%04d.%02d.%02d;%d"
                   (:year item)
                   (:month item)
                   (:day item)
                   (let [t (:type item)]
                     (if (= t :holiday) 0 1))))
         work-calendar)))

(defn create []
  (PlainTextWorkCalendarFormatter.))
