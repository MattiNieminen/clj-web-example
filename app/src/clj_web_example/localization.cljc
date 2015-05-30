(ns clj-web-example.localization)

(def dictionary {:hello "Hello World!"})

(defn tr
  [key]
  (get dictionary key))
