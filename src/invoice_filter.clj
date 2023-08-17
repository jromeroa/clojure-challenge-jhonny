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
               (not-any?
                 (fn [tax]
                   (= (:tax/category tax) :iva))
                 (:taxable/taxes item))
               (some
                 (fn [retention]
                   (and (= (:retention/category retention) :ret_fuente)
                        (= (:retention/rate retention) 1)))
                 (:retentionable/retentions item))))))))

(def filtered-items (filter-invoice-items (:invoice/items invoice)))
(println filtered-items)
