(ns problem3-invoice-test
  (:require [clojure.test :refer :all]
            [invoice-item :refer [subtotal]]))

(deftest test-subtotal-no-discount
  (is (= 500.0 (subtotal {:invoice-item/precise-quantity 5 :invoice-item/precise-price 100}))))

(deftest test-subtotal-negative-values
  (is (= 0.0 (subtotal {:invoice-item/precise-quantity 5 :invoice-item/precise-price 100 :invoice-item/discount-rate -5}))))

(deftest test-subtotal-with-discount
  (is (= 450.0 (subtotal {:invoice-item/precise-quantity 5 :invoice-item/precise-price 100 :invoice-item/discount-rate 10}))))

(deftest test-subtotal-zero-quantity
  (is (= 0.0 (subtotal {:invoice-item/precise-quantity 0 :invoice-item/precise-price 100}))))

(deftest test-subtotal-zero-price
  (is (= 0.0 (subtotal {:invoice-item/precise-quantity 5 :invoice-item/precise-price 0}))))

(deftest test-subtotal-negative-price
  (is (= 0.0 (subtotal {:invoice-item/precise-quantity 5 :invoice-item/precise-price -100}))))

(deftest test-subtotal-null-values
  (is (= 0.0 (subtotal {:invoice-item/precise-quantity nil :invoice-item/precise-price nil :invoice-item/discount-rate nil}))))

(deftest test-subtotal-missing-keys
  (is (= 0.0 (subtotal {}))))

(deftest test-subtotal-large-values
  (is (= 1000000.0 (subtotal {:invoice-item/precise-quantity 10000.00 :invoice-item/precise-price 100.00 :invoice-item/discount-rate 0}))))

(deftest test-subtotal-decimal-values
  (is (= 2.5 (subtotal {:invoice-item/precise-quantity 5 :invoice-item/precise-price 0.5 :invoice-item/discount-rate 0}))))


