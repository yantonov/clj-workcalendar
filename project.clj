(defproject cljs-workcalendar "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [clj-http "2.0.0"]
                 [enlive "1.1.6"]
                 [org.clojure/clojurescript "1.7.170"]]
  :main cljs-workcalendar.app
  :plugins [[lein-cljsbuild "1.1.1"]]
  :cljsbuild {
              :builds {
                       :production
                       {
                        :source-paths ["src-cljs"]
                        :compiler {
                                   :output-to "deploy/javascripts/main.js"
                                   :optimizations :whitespace
                                   :pretty-print false}}
                       :unittest
                       {
                        :source-paths ["src-cljs" "test-cljs"]
                        :notify-command ["phantomjs"
                                         "phantom/unit-test.js"
                                         "phantom/unit-test.html"]
                        :compiler {
                                   :output-to "target/testable.js"
                                   :optimizations :whitespace
                                   :pretty-print false}}}
              :test-commands {"unit-tests"
                              ["phantomjs"
                               "phantom/unit-test.js"
                               "phantom/unit-test.html"]}})
