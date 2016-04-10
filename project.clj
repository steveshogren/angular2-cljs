(defproject cljs-angular2 "0.1.0-SNAPSHOT"

  :description "FIXME: write description"
  :url "http://example.com/FIXME"

  :dependencies [[org.clojure/clojure "1.7.0"]
                 [selmer "0.9.8"]
                 [hiccups "0.3.0"]
                 [markdown-clj "0.9.85"]
                 [environ "1.0.1"]
                 [ring-middleware-format "0.7.0"]
                 [metosin/ring-http-response "0.6.5"]
                 [bouncer "1.0.0"]
                 [org.clojure/tools.nrepl "0.2.12"]
                 [org.webjars/bootstrap "3.3.6"]
                 [org.webjars/jquery "2.1.4"]
                 [org.clojure/tools.logging "0.3.1"]
                 [org.slf4j/slf4j-log4j12 "1.7.13"]
                 [org.apache.logging.log4j/log4j-core "2.5"]
                 [com.taoensso/tower "3.0.2"]
                 [compojure "1.4.0"]
                 [ring-webjars "0.1.1"]
                 [ring/ring-defaults "0.1.5"]
                 [ring "1.4.0" :exclusions [ring/ring-jetty-adapter]]
                 [mount "0.1.7"]
                 [org.clojure/clojurescript "1.7.170" :scope "provided"]
                 [reagent "0.5.1"]
                 [reagent-forms "0.5.13"]
                 [reagent-utils "0.1.7"]
                 [secretary "1.2.3"]
                 [org.clojure/core.async "0.2.374"]
                 [cljs-ajax "0.5.2"]
                 [org.webjars/webjars-locator-jboss-vfs "0.1.0"]
                 [org.immutant/web "2.1.1" :exclusions [ch.qos.logback/logback-classic]]]

  :min-lein-version "2.0.0"
  :uberjar-name "cljs-angular2.jar"
  :jvm-opts ["-server"]
  :resource-paths ["resources" "target/cljsbuild" "node_modules"]

  :main cljs-angular2.core

  :plugins [[lein-environ "1.0.1"]
            [lein-cljsbuild "1.1.1"]]
  :clean-targets ^{:protect false} [:target-path [:cljsbuild :builds :app :compiler :output-dir] [:cljsbuild :builds :app :compiler :output-to]]
  :cljsbuild
  {:builds
   {:app
    {:source-paths ["src-cljs"]
     :compiler
     {:output-to "target/cljsbuild/public/js/app.js"
      :output-dir "target/cljsbuild/public/js/out"
      :externs ["react/externs/react.js"]
      :pretty-print true}}}}
  
  :profiles
  {:uberjar {:omit-source true
             :env {:production true}
              :prep-tasks ["compile" ["cljsbuild" "once"]]
              :cljsbuild
              {:builds
               {:app
                {:source-paths ["env/prod/cljs"]
                 :compiler
                 {:optimizations :advanced
                  :pretty-print false
                  :closure-warnings
                  {:externs-validation :off :non-standard-jsdoc :off}}}}} 
             
             :aot :all
             :source-paths ["env/prod/clj"]}
   :dev           [:project/dev :profiles/dev]
   :test          [:project/test :profiles/test]
   :project/dev  {:dependencies [[prone "0.8.3"]
                                 [ring/ring-mock "0.3.0"]
                                 [ring/ring-devel "1.4.0"]
                                 [pjstadig/humane-test-output "0.7.1"]
                                 [lein-figwheel "0.5.0-2"]
                                 [com.cemerick/piggieback "0.2.2-SNAPSHOT"]]
                  :plugins [[lein-figwheel "0.5.0-2"]]
                   :cljsbuild
                   {:builds
                    {:app
                     {:source-paths ["env/dev/cljs"]
                      :compiler
                      {:main "cljs-angular2.app"
                       :asset-path "/js/out"
                       :optimizations :none
                       :source-map true}}}} 
                  
                  :figwheel
                  {:http-server-root "public"
                   :server-port 3449
                   :nrepl-port 7002
                   :nrepl-middleware ["cemerick.piggieback/wrap-cljs-repl"]
                   :css-dirs ["resources/public/css"]
                   :ring-handler cljs-angular2.handler/app}
                  
                  :source-paths ["env/dev/clj"]
                  :repl-options {:init-ns cljs-angular2.core}
                  :injections [(require 'pjstadig.humane-test-output)
                               (pjstadig.humane-test-output/activate!)]
                  ;;when :nrepl-port is set the application starts the nREPL server on load
                  :env {:dev        true
                        :port       3000
                        :nrepl-port 7000}}
   :project/test {:env {:test       true
                        :port       3001
                        :nrepl-port 7001}}
   :profiles/dev {}
   :profiles/test {}})
