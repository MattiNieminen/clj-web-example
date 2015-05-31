(ns clj-web-example.test-utils
  (:require [com.stuartsierra.component :as component]
            [clj-web-example.system :as system]
            [clj-web-example.routes :as routes]
            [monger.db :as db]
            [org.httpkit.client :as http]
            [clojure.data.json :as json]))

(defonce test-system (component/start (system/system "test" routes/app)))

(defn database-fixture
  [f]
  (db/drop-db (get-in test-system [:mongodb :database]))
  (f))

(defn http-get
  [url]
  (let [response @(http/get url)
        body (json/read-json (:body response))]
    (assoc response :body body)))

(defn http-post
  [url body]
  @(http/post url {:body (json/write-str body)
                   :headers {"Content-Type" "application/json"}}))
