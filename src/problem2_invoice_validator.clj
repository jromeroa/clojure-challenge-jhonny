(ns problem2-invoice-validator
  (:require [clojure.spec.alpha :as s]
            [cheshire.core :as json]
            [invoice-spec :as spec]))

(defn load-json-file [filename]
  (try
    (let [content (slurp filename)]
      (json/parse-string content true))
    (catch Exception e
      (println "Error reading or parsing JSON file:" (.getMessage e)))))

(defn validate-invoice [filename]
  (let [json-data (load-json-file filename)]
    (when json-data
      (let [result (s/valid? ::spec/invoice json-data)]
        (if result
          (println "Invoice is valid")
          (do
            (println "Invoice is not valid")
            (println "Validation errors:")
            (doseq [explanation (s/explain-data ::spec/invoice json-data)]
              (println explanation))))))))

(defn -main []  ;Define function main for test the validator
  (let [filename "invoice.json"]
    (validate-invoice filename)))