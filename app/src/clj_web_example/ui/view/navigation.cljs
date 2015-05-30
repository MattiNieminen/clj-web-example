(ns clj-web-example.ui.view.navigation
  (:require [clj-web-example.localization :refer [tr]]))

(defn navigation-link
  [content href]
  [:a {:class "navigation-link"
       :href href} content])

(defn navigation
  []
  [:div#navigation
   [navigation-link (tr :messages) "#"]
   [navigation-link (tr :new-message) "#new"]])
