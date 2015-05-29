(ns clj-web-example.component.config
  (:require [potpuri.core :as potpuri]
            [clojure.java.io :as io]
            [com.stuartsierra.component :as component]))

(defn import-config
  [profile]
  (apply potpuri/deep-merge
         (map (comp read-string slurp)
              (keep io/resource ["config.edn"
                                 (format "%s-config.edn" profile)]))))

(defrecord Config [profile]
  component/Lifecycle
  
  (start [this]
    (println "Loading configuration with profile" profile)
    (assoc this :loaded (import-config profile)))
  
  (stop [this]
    (dissoc this :loaded)))

(defn new-config
  [profile]
  (map->Config {:profile profile}))
