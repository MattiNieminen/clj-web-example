(ns clj-web-example.ui.view.new-message
  (:require [clj-web-example.ui.view.view :as view]))

(defmethod view/render-view "new-message" []
  [:div#content
   [:h2 "Here will be form!"]])
