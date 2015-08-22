(ns user
  (:require [clj-web-example.system :as system]
            [clj-web-example.routes :as routes]
            [clojure.tools.namespace.repl :as repl]
            [com.stuartsierra.component :as component]))

(def dev-system nil)

(defn init
  []
  (alter-var-root #'dev-system (constantly (system/system nil routes/app))))

(defn start
  []
  (alter-var-root #'dev-system component/start))

(defn stop
  []
  (alter-var-root #'dev-system (fn [s] (when s (component/stop s)))))

(defn go
  []
  (init)
  (start))

(defn reset
  []
  (stop)
  (repl/refresh :after 'user/go))