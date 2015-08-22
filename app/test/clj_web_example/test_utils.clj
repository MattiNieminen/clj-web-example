(ns clj-web-example.test-utils
  (:require [com.stuartsierra.component :as component]
            [clj-web-example.system :as system]
            [clj-web-example.routes :as routes]
            [monger.db :as db]
            [org.httpkit.client :as http]
            [clojure.data.json :as json]))

(defonce test-system nil)

(defn system-fixture
  [f]
  (alter-var-root #'test-system (constantly (system/system "test" routes/app)))
  (alter-var-root #'test-system component/start)
  (db/drop-db (get-in test-system [:mongodb :database]))
  (f)
  (alter-var-root #'test-system (fn [s] (when s (component/stop s)))))

(defn get-database
  []
  (get-in test-utils/test-system [:mongodb :database]))

(defn http-get
  [url]
  (let [response @(http/get url)
        body (json/read-json (:body response))]
    (assoc response :body body)))

(defn http-post
  [url body]
  @(http/post url {:body (json/write-str body)
                   :headers {"Content-Type" "application/json"}}))
