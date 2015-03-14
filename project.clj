(defproject cljs-workcalendar "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [clj-http "1.0.1"]
                 [enlive "1.1.5"]
                 [org.clojure/clojurescript "0.0-3058"]]
  :main cljs-workcalendar.app
  :plugins [[lein-cljsbuild "1.0.5"]]
  :cljsbuild {
              :builds [{
                        ;; The path to the top-level ClojureScript source directory:
                        :source-paths ["src-cljs"]
                        ;; The standard ClojureScript compiler options:
                        ;; (See the ClojureScript compiler documentation for details.)
                        :compiler {
                                   :output-to "deploy/javascripts/main.js"
                                   :optimizations :whitespace
                                   :pretty-print false}}]})
