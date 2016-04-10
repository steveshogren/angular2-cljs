(ns cljs-angular2.config
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[cljs-angular2 started successfully]=-"))
   :middleware identity})
