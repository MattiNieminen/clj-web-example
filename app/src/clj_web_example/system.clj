(ns clj-web-example.system
  (:require [clj-web-example.component.config :as config]
            [clj-web-example.component.mongodb :as mongodb]
            [clj-web-example.component.httpkit :as httpkit]
            [com.stuartsierra.component :as component]))

(defn system
  [profile routes]
  (component/system-map
    :config (config/new-config profile)
    :mongodb (component/using (mongodb/new-mongodb) [:config])
    :httpkit (component/using (httpkit/new-httpkit routes) [:config])))
