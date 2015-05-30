(ns clj-web-example.main
  (:gen-class)
  (:require [clj-web-example.system :as system]
            [com.stuartsierra.component :as component]
            [clj-web-example.routes :as routes]))

(defn -main
  [& args]
  (component/start (system/system (first args) routes/app)))
