(ns clj-web-example.system
  (:require [clj-web-example.component.config :as config]
            [clj-web-example.component.httpkit :as httpkit]
            [com.stuartsierra.component :as component]))

(defn system
  [profile routes]
  (component/system-map
    :config (config/new-config profile)
    :httpkit (component/using (httpkit/new-httpkit routes) [:config])))
