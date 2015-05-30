(ns ^:figwheel-always clj-web-example.ui.main
  (:require [reagent.core :as reagent]))

(defn simple-component []
  [:div
   [:h1 "Hello World!"]])

(defn init!
  []
  (.log js/console "Here we go! Rendering the components...")
  (reagent/render [simple-component] (js/document.getElementById "app")))

(init!)
