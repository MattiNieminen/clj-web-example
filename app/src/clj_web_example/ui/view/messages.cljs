(ns clj-web-example.ui.view.messages
  (:require [reagent.core :as reagent]
            [clj-web-example.ui.utils :as utils]
            [clj-web-example.ui.route :as route]
            [clj-web-example.ui.view.view :as view]))

(defonce messages-state (reagent/atom nil))

(defn get-messages
  []
  (utils/http-get! "/api/messages" #(reset! messages-state (:body %))))

(defn message
  [msg]
  [:div
   [:h3 (:sender msg)]
   [:p (:body msg)]])

(defn messages
  []
  [:div#content
   [:h2 "Messages"]
   [:div
    (for [msg @messages-state]
      ^{:key (:_id msg)} [message msg])]])

(def messages-with-callback
  (with-meta messages {:component-did-mount get-messages}))

(defmethod view/render-view "messages" []
  [messages-with-callback])
