(ns clj-web-example.ui.view.messages
  (:require [clj-web-example.ui.view.view :as view]))

(defmethod view/render-view "messages" []
  [:div#content
   [:h2 "Here will be messages!"]])
