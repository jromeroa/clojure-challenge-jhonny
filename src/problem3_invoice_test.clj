(ns problem3-invoice-test
  (:require [clojure.test :refer :all]
            [invoice-item :refer [subtotal]]))

(deftest test-subtotal-large-values
  (is (= 1000000 (subtotal {:precise-quantity 10000 :precise-price 100 :discount-rate 0}))))



