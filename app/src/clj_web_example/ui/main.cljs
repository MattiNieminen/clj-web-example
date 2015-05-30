(ns ^:figwheel-always clj-web-example.ui.main
  (:require [clj-web-example.localization :refer [tr]]
            [reagent.core :as reagent]))

(defn simple-component []
  [:div
   [:h1 (tr :hello)]])

(defn init!
  []
  (.log js/console "Here we go! Rendering the components...")
  (reagent/render [simple-component] (js/document.getElementById "app")))

(init!)
