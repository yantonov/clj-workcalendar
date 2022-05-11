(defproject cljs-workcalendar "0.1.0-SNAPSHOT"
  :description "front side work calendar"
  :url "https://github.com/yantonov/cljs-workcalendar"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies
  [[org.clojure/clojure "1.11.1"]
   [clj-http "3.12.3"]
   [enlive "1.1.6"]
   [org.clojure/clojurescript "1.11.4"]]

  :main clj-workcalendar.app

  :source-paths ["src" "src-gen"]
  :test-paths ["test" "test-gen"]

  :plugins
  [[lein-cljsbuild "1.1.2"]]

  :cljsbuild
  {:builds
   {:production
    {:source-paths ["src"]
     :compiler
     {:output-to "target/work-calendar.js"
      :source-map "target/work-calendar.js.map"
      :optimizations :advanced
      :pretty-print false
      :parallel-build true}}

    :unittest
    {:source-paths ["src" "test"]
     :compiler
     {:output-to "target/testable-work-calendar.js"
      :optimizations :whitespace
      :pretty-print false
      :parallel-build true}
     :notify-command ["phantomjs"
                      "phantom/unit-test.js"
                      "phantom/unit-test.html"]}}

   :test-commands {"unit-tests"
                   ["phantomjs"
                    "phantom/unit-test.js"
                    "phantom/unit-test.html"]}})
