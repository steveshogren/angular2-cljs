(ns ^:figwheel-no-load cljs-angular2.app
  (:require [cljs-angular2.core :as core]
            [figwheel.client :as figwheel :include-macros true]))

(enable-console-print!)

(defn mount-component []
  ((.. js/ng -platform -browser -bootstrap)
   (.-AppComponent (.-app js/window))))

(figwheel/watch-and-reload :websocket-url "ws://localhost:3449/figwheel-ws"
                           :on-jsload mount-component)

(defonce only-attach-listener-once
  (.addEventListener js/document "DOMContentLoaded" mount-component))
