(ns clj-workcalendar.datasource.workcalendarsource)

(defprotocol WorkCalendarSource
  (get-work-calendar [this]))
