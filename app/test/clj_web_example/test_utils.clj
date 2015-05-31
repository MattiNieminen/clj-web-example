(ns clj-web-example.test-utils
  (:require [com.stuartsierra.component :as component]
            [clj-web-example.system :as system]
            [clj-web-example.routes :as routes]))

(defonce test-system (component/start (system/system "test" routes/app)))
