(defproject clj-web-example "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "https://github.com/MattiNieminen/clj-web-example"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0-beta3"]
                 [org.clojure/tools.namespace "0.2.11"]
                 [org.clojure/tools.logging "0.3.1"]
                 [ch.qos.logback/logback-classic "1.1.3"]
                 [com.stuartsierra/component "0.2.3"]
                 [metosin/potpuri "0.2.2"]
                 [http-kit "2.1.19"]
                 [metosin/compojure-api "0.21.0"]
                 [metosin/ring-swagger-ui "2.1.5-M2"]
                 [prismatic/schema "0.4.3"]
                 [hiccup "1.0.5"]
                 [org.clojure/clojurescript "0.0-3269"]
                 [reagent "0.5.0"]
                 [secretary "1.2.3"]
                 [com.novemberain/monger "2.0.1"]
                 [clj-time "0.9.0"]
                 [org.clojure/core.async "0.1.346.0-17112a-alpha"]
                 [cljs-http "0.1.31"]]
  :target-path "target/%s"
  :profiles {:dev {:source-paths ["dev"]
                   :resource-paths ["target/dev"]
                   :less {:target-path "target/dev/public/css"}}
             :deploy {:main clj-web-example.main
                      :resource-paths ["target/deploy"]
                      :less {:target-path "target/deploy/public/css"}
                      :aot :all}}
  :plugins [[lein-cljsbuild "1.0.6"]
            [lein-figwheel "0.3.3"]
            [deraen/lein-less4j "0.2.1"]
            [lein-pdo "0.1.1"]]
  :less {:source-paths ["less"]
         :source-map true
         :compression true}
  :figwheel {:css-dirs ["target/dev/public/css"]
             :server-logfile "clj-app-logs/figwheel_server.log"}
  :cljsbuild {:builds [{:id "dev"
                        :source-paths ["src/"]
                        :figwheel true
                        :compiler {:main clj-web-example.ui.main
                                   :optimizations :none
                                   :output-to "target/dev/public/js/main.js"
                                   :output-dir "target/dev/public/js"
                                   :asset-path "/static/js"}}
                       {:id "deploy"
                        :source-paths ["src/"]
                        :compiler {:main clj-web-example.ui.main
                                   :optimizations :advanced
                                   :output-to "target/deploy/public/js/main.js"
                                   :output-dir "target/deploy/public/js"
                                   :pretty-print false}}]}
  :auto-clean false
  :uberjar-name "clj-web-example.jar"
  :aliases {"develop" ["do" "clean"
                       ["pdo" ["less4j" "auto"] ["figwheel"]]]
            "build" ["with-profile" "deploy" "do" "clean"
                     ["less4j" "once"]["cljsbuild" "once" "deploy"] "uberjar"]})
