(ns clj-web-example.ui.view.new-message
  (:require [reagent.core :as reagent]
            [clj-web-example.ui.utils :as utils]
            [clj-web-example.ui.route :as route]
            [clj-web-example.localization :refer [tr]]
            [clj-web-example.ui.view.view :as view]))

(defonce new-message (reagent/atom nil))

(defn send-message
  []
  (utils/http-post! "/api/messages"
                   @new-message
                   #(do
                      (reset! new-message nil)
                      (route/modify-location-hash! "#"))))

(defn atom-input
  [label-text atom key]
  [:div
   [:label label-text]
   [:input {:type "text"
            :value (key @atom)
            :on-change #(swap! atom assoc key (-> % .-target .-value))}]])

(defn new-message-form
  []
  [:div
   [atom-input (tr :sender) new-message :sender]
   [atom-input (tr :message) new-message :body]
   [:button {:type "button"
             :disabled (or
                         (empty? (:sender @new-message))
                         (empty? (:body @new-message)))
             :on-click send-message}
    (tr :submit)]])

(defmethod view/render-view "new-message" []
  [:div#content
   [:h2 "New message"]
   [new-message-form]])
