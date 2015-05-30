(ns clj-web-example.ui.view.view
  (:require [clj-web-example.localization :refer [tr]]))

(defmulti render-view :id)

(defmethod render-view :default []
  [:div#content
   [:h2 (tr :not-found)]])
