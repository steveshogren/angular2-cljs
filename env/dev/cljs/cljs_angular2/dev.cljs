(ns ^:figwheel-no-load cljs-angular2.app
  (:require [cljs-angular2.core :as core]
            [figwheel.client :as figwheel :include-macros true]))

(enable-console-print!)

(defn reload []
  (core/init!)
  ((.. js/ng -platform -browser -bootstrap)
   (.-AppComponent (.-app js/window))))

(figwheel/watch-and-reload :websocket-url "ws://localhost:3449/figwheel-ws"
                           :on-jsload reload)

(core/init!)
(defonce only-attach-listener-once
  (.addEventListener js/document "DOMContentLoaded"
                     (fn []
                       ((.. js/ng -platform -browser -bootstrap)
                        (.-AppComponent (core/get-app))))))


;; (function(app) {
;;   document.addEventListener('DOMContentLoaded', function() {
;;     ng.platform.browser.bootstrap(app.AppComponent);
;;   });
;; })(window.app || (window.app = {}));

;; (defonce af
;;   (let [x (clj->js {})]
;;     (set! (.. x -name) (clj->js {}))
;;     (set! (.. x -name -horse) js/alert)
;;     ((.. x -name -horse) "HELLO")))
