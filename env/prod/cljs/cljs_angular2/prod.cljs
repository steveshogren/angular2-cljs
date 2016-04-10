(ns cljs-angular2.app
  (:require [cljs-angular2.core :as core]))

;;ignore println statements in prod
(set! *print-fn* (fn [& _]))

(core/init!)
