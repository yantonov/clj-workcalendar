(ns clj-workcalendar.datasource.calendar-test-data
  (:require  [clojure.test :refer :all]))

(defn calendar []
  {2021 [[1 1 :holiday] [1 4 :holiday] [1 5 :holiday] [1 6 :holiday] [1 7 :holiday] [1 8 :holiday] [2 20 :workday] [2 22 :holiday] [2 23 :holiday] [3 8 :holiday] [5 3 :holiday] [5 10 :holiday] [6 14 :holiday] [11 4 :holiday] [11 5 :holiday] [12 31 :holiday]]
   2022 [[1 3 :holiday] [1 4 :holiday] [1 5 :holiday] [1 6 :holiday] [1 7 :holiday] [2 23 :holiday] [3 5 :workday] [3 7 :holiday] [3 8 :holiday] [5 2 :holiday] [5 3 :holiday] [5 9 :holiday] [5 10 :holiday] [6 13 :holiday] [11 4 :holiday]]
   2023 [[1 2 :holiday] [1 3 :holiday] [1 4 :holiday] [1 5 :holiday] [1 6 :holiday] [2 23 :holiday] [2 24 :holiday] [3 8 :holiday] [5 1 :holiday] [5 8 :holiday] [5 9 :holiday] [6 12 :holiday] [11 6 :holiday]]
   2019 [[1 1 :holiday]
         [1 2 :holiday]
         [1 3 :holiday]
         [1 4 :holiday]
         [1 7 :holiday]
         [1 8 :holiday]
         [3 8 :holiday]
         [5 1 :holiday]
         [5 2 :holiday]
         [5 3 :holiday]
         [5 9 :holiday]
         [5 10 :holiday]
         [6 12 :holiday]
         [11 4 :holiday]]
   2018 [[1 1  :holiday]
         [1 2  :holiday]
         [1 3  :holiday]
         [1 4  :holiday]
         [1 5  :holiday]
         [1 8  :holiday]
         [2 23 :holiday]
         [3 8  :holiday]
         [3 9  :holiday]
         [4 28 :workday]
         [4 30 :holiday]
         [5 1  :holiday]
         [5 2  :holiday]
         [5 9  :holiday]
         [6 9  :workday]
         [6 11 :holiday]
         [6 12 :holiday]
         [11 5 :holiday]
         [12 29 :workday]
         [12 31 :holiday]]
   2017 [[1 2  :holiday]
         [1 3  :holiday]
         [1 4  :holiday]
         [1 5  :holiday]
         [1 6  :holiday]
         [2 23 :holiday]
         [2 24 :holiday]
         [3 8  :holiday]
         [5 1  :holiday]
         [5 8  :holiday]
         [5 9  :holiday]
         [6 12  :holiday]
         [11 6 :holiday]]
   2016 [[1 1  :holiday]
         [1 4  :holiday]
         [1 5  :holiday]
         [1 6  :holiday]
         [1 7  :holiday]
         [1 8  :holiday]
         [2 20 :workday]
         [2 22 :holiday]
         [2 23 :holiday]
         [3 7  :holiday]
         [3 8  :holiday]
         [5 2  :holiday]
         [5 3  :holiday]
         [5 9  :holiday]
         [6 13 :holiday]
         [11 4 :holiday]]
   2015 [[1 1  :holiday]
         [1 2  :holiday]
         [1 5  :holiday]
         [1 6  :holiday]
         [1 7  :holiday]
         [1 8  :holiday]
         [1 9  :holiday]
         [2 23 :holiday]
         [3 9  :holiday]
         [5 1  :holiday]
         [5 4  :holiday]
         [5 11 :holiday]
         [6 12 :holiday]
         [11 4 :holiday]]
   2014 [[1 1  :holiday]
         [1 2  :holiday]
         [1 3  :holiday]
         [1 6  :holiday]
         [1 7  :holiday]
         [1 8  :holiday]
         [3 10 :holiday]
         [5 1  :holiday]
         [5 2  :holiday]
         [5 9  :holiday]
         [6 12 :holiday]
         [6 13 :holiday]
         [11 3 :holiday]
         [11 4 :holiday]]
   2013 [[1 1  :holiday]
         [1 2  :holiday]
         [1 3  :holiday]
         [1 4  :holiday]
         [1 7  :holiday]
         [1 8  :holiday]
         [3 8  :holiday]
         [5 1  :holiday]
         [5 2  :holiday]
         [5 3  :holiday]
         [5 9  :holiday]
         [5 10  :holiday]
         [6 12  :holiday]
         [11 4  :holiday]]
   2012 [[1 2   :holiday]
         [1 3   :holiday]
         [1 4   :holiday]
         [1 5   :holiday]
         [1 6   :holiday]
         [1 9   :holiday]
         [2 23  :holiday]
         [3 8   :holiday]
         [3 9   :holiday]
         [3 11  :workday]
         [4 28  :workday]
         [4 30  :holiday]
         [5 1   :holiday]
         [5 5   :workday]
         [5 7   :holiday]
         [5 8   :holiday]
         [5 9   :holiday]
         [5 12  :workday]
         [6 9   :workday]
         [6 11  :holiday]
         [6 12  :holiday]
         [11 5  :holiday]
         [12 29 :workday]
         [12 31 :holiday]]})
