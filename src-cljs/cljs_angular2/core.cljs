(ns cljs-angular2.core
  (:require-macros [hiccups.core :as hiccups :refer [html]])
  (:require
            [goog.events :as events]
            [goog.history.EventType :as HistoryEventType]
            [markdown.core :refer [md->html]]
            [ajax.core :refer [GET POST]])
  (:import goog.History))

(defn get-app []
   (or (.-app js/window)
       (set! (.-app js/window) #js {})))

(defn add [x y ] (+ x y))

(defn init! []
  (let [app (get-app)
        c (.Component (.-core js/ng)
                      #js {:selector "my-app"
                           :template (html [:div
                                            [:h1 "My first Angular 2 app"]
                                            [:div [:h2 "test"]]
                                            [:div [:h3 "test2"]]])})
        c (.Class c #js {:constructor (fn [])})]
    (set! (.-AppComponent app) c)))

