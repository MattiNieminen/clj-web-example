(ns ^:figwheel-always clj-web-example.ui.main
  (:require [clj-web-example.localization :refer [tr]]
            [reagent.core :as reagent]
            [clj-web-example.ui.view.navigation :as navigation]))

(defn main-view
  []
  [:div
   [:h1 (tr :title)]
   [navigation/navigation]])

(defn init!
  []
  (.log js/console "Here we go! Rendering the components...")
  (reagent/render [main-view] (js/document.getElementById "app")))

(init!)
