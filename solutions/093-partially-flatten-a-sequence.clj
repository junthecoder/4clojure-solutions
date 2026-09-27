;; 4Clojure #93 — Partially Flatten a Sequence [Medium]
;; Solved: 2016-12-16

(fn flat [xs]
  (if (coll? xs)
    (if-let [[x & xs] xs]
      (concat (if (some coll? x)
                (flat x)
                [x])
              (flat xs)))
    xs))
