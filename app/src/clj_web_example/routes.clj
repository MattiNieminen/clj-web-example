(ns clj-web-example.routes
  (:require [compojure.api.sweet :refer :all]
            [schema.core :as s]
            [ring.util.response :as response]))

(def Message {:body s/Str})

(defapi app
  (swagger-ui "/swagger-ui")
  (swagger-docs {:info {:title "clj-web-example API"
                        :description "Example API for example application."}})
  (context* "/api" []
    :tags ["API"]
    
    (POST* "/echo" []
      :summary "Echoes the given message."
      :return  (s/maybe Message)
      :body    [message (s/maybe Message)]
      (response/response message))))
