(ns clj-web-example.system
  (:require [clj-web-example.component.config :as config]
            [com.stuartsierra.component :as component]))

(defn system
  [profile]
  (component/system-map
    :config (config/new-config profile)))
