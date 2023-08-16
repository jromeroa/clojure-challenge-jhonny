(def invoice (clojure.edn/read-string (slurp "invoice.edn")))

(defn filter-invoice-items [items]
  (filter (fn [item]) items))

(def filtered-items (filter-invoice-items (:invoice/items invoice)))
