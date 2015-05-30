(ns clj-web-example.localization_test
  (:require [clojure.test :refer :all]
            [clj-web-example.localization :refer :all]))

(deftest tr-test
  (testing "Translations are fetched correctly."
    (is (= (tr :hello) "Hello World!"))))
