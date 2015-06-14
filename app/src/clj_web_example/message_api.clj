(ns clj-web-example.message-api
  (:require [schema.core :as s]
            [clj-web-example.utils :as utils]
            [monger.collection :as mc]
            [schema.coerce :as coerce]))

(def Message {:sender s/Str
              :body s/Str})

(def SavableMessage (merge Message utils/Savable))

(defn message-matcher
  [schema]
  (if (= schema SavableMessage) utils/->savable))

(defn get-messages
  [database]
  (mc/find-maps database "messages"))

(defn save-message
  [database message]
  (mc/insert-and-return
    database
    "messages"
    ((coerce/coercer SavableMessage message-matcher) message)))
