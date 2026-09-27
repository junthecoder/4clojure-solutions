;; 4Clojure #67 — Prime Numbers [Medium]
;; Solved: 2016-12-15

(fn [n]
  (letfn [(prime? [x] (and (> x 1) (every? #(not= 0 (mod x %)) (range 2 x))))]
    (take n (filter prime? (range)))))
