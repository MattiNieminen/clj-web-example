(ns clj-web-example.message-api-test
  (:require [clojure.test :refer :all]
            [clj-web-example.test-utils :as test-utils]
            [monger.collection :as mc]
            [clj-web-example.message-api :refer :all]))

(use-fixtures :each test-utils/database-fixture)

(def sender-1 "Man from another place")
(def body-1 "Let's rock!")
(def message-1 {:sender sender-1
                :body body-1})

(def sender-2 "Giant")
(def body-2 "The owls are not what they seem!")
(def message-2 {:sender sender-2
                :body body-2})

(def url "http://localhost:8181/api/messages")

(def database (get-in test-utils/test-system [:mongodb :database]))

(deftest message-api-test
  (testing "Messages can be saved into database."
    (is (mc/empty? database "messages"))
    (let [response-1 (test-utils/http-post url message-1)
          response-2 (test-utils/http-post url message-2)]
      (is (= 200 (:status response-1)))
      (is (= 200 (:status response-2)))
      (is (= 2 (mc/count database "messages"))))
    (let [response (test-utils/http-get url)
          saved-message-1 (first (:body response))
          saved-message-2 (second (:body response))]
      (is (= 200 (:status response) 200))
      (is (= sender-1 (:sender saved-message-1)))
      (is (= body-1 (:body saved-message-1)))
      (is (contains? saved-message-1 :_id))
      (is (contains? saved-message-1 :timestamp))
      (is (= sender-2 (:sender saved-message-2)))
      (is (= body-2 (:body saved-message-2)))
      (is (contains? saved-message-2 :_id))
      (is (contains? saved-message-2 :timestamp)))))
