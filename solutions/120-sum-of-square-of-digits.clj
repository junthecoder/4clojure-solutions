;; 4Clojure #120 — Sum of square of digits [Easy]
;; Solved: 2016-12-15

(fn [xs]
  (letfn [(sq [x] (* x x))]
    (->> xs
         (filter (fn [x] (< x (apply + (map #(sq (- (int %) 48)) (str x))))))
         count)))
