(ns invoice-item)

(defn- discount-factor [{:invoice-item/keys [discount-rate]
                         :or                {discount-rate 0}}]
  (- 1 (/ discount-rate 100.0)))

(defn subtotal
  [{:invoice-item/keys [precise-quantity precise-price discount-rate]
    :as                item
    :or                {discount-rate 0}}]
  (if (and (not (nil? precise-quantity))
           (not (nil? precise-price))
           (not (nil? discount-rate))
           (not (neg? precise-quantity))
           (not (neg? precise-price))
           (not (neg? discount-rate)))
    (* precise-price precise-quantity (discount-factor item))
    0.0)) ; Return 0 or any other default value if any of the values is nil

