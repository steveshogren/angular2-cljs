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

(defonce heroes (atom []))

(let [app (get-app)
      c (.Component (.-core js/ng)
                    #js {:selector "my-app"
                         :template (html [:div
                                          [:h1 " Demo"]
                                          [:div [:h2 "Hero List:"]]
                                          "
<input #newHero (keyup.enter)=\"addHero(newHero.value); newHero.value='' \" >
<button (click)=\"addHero(newHero.value); newHero.value='' \">Add</button>
<ul><li *ngFor=\"#hero of heroes()\">{{hero}}</li></ul>
"
                                          ])})
      c (.Class c #js {:constructor (fn [])
                       :addHero (fn [hero]
                                  (swap! heroes #(conj % hero)))
                       :heroes (fn [] @heroes)
                       })]
  (set! (.-AppComponent app) c))


















