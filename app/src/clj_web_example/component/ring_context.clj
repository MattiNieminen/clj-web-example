(ns clj-web-example.component.ring-context
  (:require [com.stuartsierra.component :as component]))

(defn wrap-database [handler database]
  (fn [request]
    (handler (assoc request :database database))))

(defrecord Ring-Context [mongodb initial-handler]
  component/Lifecycle
  
  (start [this]
    (assoc this :handler (wrap-database initial-handler (:database mongodb))))
  
  (stop [this]
    (dissoc this :handler)))

(defn new-ring-context
  [handler]
  (map->Ring-Context {:initial-handler handler}))
