(ns clj-web-example.component.httpkit
  (:require [com.stuartsierra.component :as component]
            [clojure.tools.logging :as log]
            [org.httpkit.server :as httpkit]
            [clojure.test :as test]))

(defrecord HttpKit [config routes stop-handle]
  component/Lifecycle
  
  (start [this]
    (let [http-config (get-in config [:loaded :http])]
      (log/info "Starting HTTP Kit @ port" (:port http-config))
      (assoc this :stop-handle (httpkit/run-server routes http-config))))
  
  (stop [this]
    (when (test/function? stop-handle) (stop-handle))
    (dissoc this :stop-fn)))

(defn new-httpkit
  [routes]
  (map->HttpKit {:routes routes}))
