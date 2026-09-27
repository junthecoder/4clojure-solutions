;; 4Clojure #118 — Re-implement Map [Easy]
;; Solved: 2016-12-04

(fn map' [f xs]
  (when (seq xs)
    (lazy-seq (cons (f (first xs)) (map' f (rest xs))))))
