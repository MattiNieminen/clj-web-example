(ns clj-web-example.main
  (:gen-class)
  (:require [clj-web-example.system :as system]
            [com.stuartsierra.component :as component]))

(defn -main
  [& args]
  (component/start (system/system (first args))))
