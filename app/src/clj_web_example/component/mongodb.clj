(ns clj-web-example.component.mongodb
  (:require [com.stuartsierra.component :as component]
            [clojure.tools.logging :as log]
            [monger.core :as mg]
            [monger.joda-time]))

(defrecord MongoDb [config connection]
  component/Lifecycle
  
  (start [this]
    (let [mongodb-config (get-in config [:loaded :mongodb])
          connection-config (:connection mongodb-config)
          new-connection (mg/connect connection-config)]
      (log/info "Connected to MongoDB @"
                (str (:host connection-config) ":" (:port connection-config)))
      (assoc this
             :connection new-connection
             :database (mg/get-db new-connection (:database mongodb-config)))))
  
  (stop [this]
    (when connection (mg/disconnect connection))
    (dissoc this :connection :database)))

(defn new-mongodb
  []
  (map->MongoDb {}))
