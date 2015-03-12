(ns cljs-workcalendar.format.formatinterface)

(defprotocol ICanFormatWorkCalendar
  (format-work-calendar [this workcalendar] "format work calendar"))
