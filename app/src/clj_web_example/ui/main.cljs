(ns ^:figwheel-always clj-web-example.ui.main
  (:require [clj-web-example.localization :refer [tr]]
            [reagent.core :as reagent]
            [clj-web-example.ui.view.navigation :as navigation]
            [clj-web-example.ui.view.view :as view]
            [clj-web-example.ui.view.views]
            [clj-web-example.ui.route :as route]))

(defn main-view
  []
  [:div
   [:h1 (tr :title)]
   [navigation/navigation]
   (view/render-view @route/route)])

(defn init!
  []
  (.log js/console "Here we go! Rendering the components...")
  (route/init-hook!)
  (reagent/render [main-view] (js/document.getElementById "app")))

(init!)
