(ns cljs-workcalendar.spider.workcalendarsource)

(defprotocol WorkCalendarSource
  (get-work-calendar [this]))
