(ns clj-web-example.utils
  (:require [schema.core :as s])
  (:import [org.joda.time DateTime]
           [org.joda.time DateTimeZone]))

;
; Monger (from Monger guide).
;
(DateTimeZone/setDefault DateTimeZone/UTC)

(defn object-id
  []
  (str (org.bson.types.ObjectId.)))

;
; Coercion helpers before saving documents to MongoDB.
;
(def Savable {:_id s/Str
              :timestamp DateTime})

(defn ->savable
  [coll]
  (assoc coll
         :_id (object-id)
         :timestamp (DateTime/now)))
