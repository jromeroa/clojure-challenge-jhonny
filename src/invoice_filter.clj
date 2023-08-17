(def invoice (clojure.edn/read-string (slurp "/Users/jhonny/projects/clojure-challenge-jhonny/invoice.edn")))

(defn filter-invoice-items [items]
  (->> items
       (filter
         (fn [item]
           (or
             (and
               (some
                 (fn [tax]
                   (and (= (:tax/category tax) :iva)
                        (= (:tax/rate tax) 19)))
                 (:taxable/taxes item))
               (not-any?
                 (fn [retention]
                   (= (:retention/category retention) :ret_fuente))
                 (:retentionable/retentions item)))
             (and
               )))
         )))

(def filtered-items (filter-invoice-items (:invoice/items invoice)))
(println filtered-items)
