(ns clj-web-example.system-test
  (:require [clojure.test :refer :all]
            [clj-web-example.test-utils :as test-utils]))

(deftest system-test
  (testing "System contains the required components."
    (is (contains? test-utils/test-system :config))
    (is (contains? test-utils/test-system :mongodb))
    (is (contains? test-utils/test-system :ring-context))
    (is (contains? test-utils/test-system :httpkit)))
  (testing "Config is loaded correctly and merged from multiple files."
    (let [config (:config test-utils/test-system)]
      (is (= "test" (:profile config)))
      (is (= 8181 (get-in config [:loaded :http :port])))))
   (testing "MongoDB connection is initialized correctly."
     (let [mongodb (:mongodb test-utils/test-system)]
       (is (contains? mongodb :connection ))
       (is (contains? mongodb :database))))
   (testing "Ring-context is initialized correctly."
     (let [ring-context (:ring-context test-utils/test-system)]
       (is (contains? ring-context :handler ))))
   (testing "Http Kit -based server is initialized correctly.)."
     (let [httpkit (:httpkit test-utils/test-system)]
       (is (contains? httpkit :stop-handle )))))
