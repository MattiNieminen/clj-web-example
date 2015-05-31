(ns clj-web-example.message-api
  (:require [schema.core :as s]
            [clj-web-example.utils :as utils]
            [monger.collection :as mc]
            [ring.util.response :as response]
            [schema.coerce :as coerce]))

(def Message {:sender s/Str
              :body s/Str})

(def SavableMessage (merge Message utils/Savable))

(defn message-matcher
  [schema]
  (if (= schema SavableMessage) utils/->savable))

(defn get-messages
  [{database :database}]
  (response/response (mc/find-maps database "messages")))

(defn save-message
  [{message :body-params
    database :database}]
  (response/response
    (mc/insert-and-return
      database
      "messages"
      ((coerce/coercer SavableMessage message-matcher) message))))
