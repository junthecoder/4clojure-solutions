;; 4Clojure #158 — Decurry [Medium]
;; Solved: 2016-12-15

(fn [f]
  (fn [& xs]
    (reduce #(%1 %2) f xs)))
