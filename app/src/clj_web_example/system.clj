(ns clj-web-example.system
  (:require [clj-web-example.component.config :as config]
            [clj-web-example.component.mongodb :as mongodb]
            [clj-web-example.component.ring-context :as ring-context]
            [clj-web-example.component.httpkit :as httpkit]
            [com.stuartsierra.component :as component]))

(defn system
  [profile ring-handler]
  (component/system-map
    :config (config/new-config profile)
    :mongodb (component/using (mongodb/new-mongodb) [:config])
    :ring-context (component/using
                    (ring-context/new-ring-context ring-handler) [:mongodb])
    :httpkit (component/using (httpkit/new-httpkit) [:config :ring-context])))
