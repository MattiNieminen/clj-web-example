(ns clj-web-example.ui.utils
  (:require [cljs.core.async :refer [<!]]
            [cljs-http.client :as http])
  (:require-macros [cljs.core.async.macros :refer [go]]))

(defn http-get
  [url f]
  (go
    (let [response (<! (http/get url))]
      (f response))))

(defn http-post
  [url body f]
  (go
    (let [response (<! (http/post url {:edn-params body}))]
      (f response))))
