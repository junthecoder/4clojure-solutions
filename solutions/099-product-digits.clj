;; 4Clojure #99 — Product Digits [Easy]
;; Solved: 2016-12-05

(fn [x y] (map #(Character/digit % 10) (str (* x y))))
