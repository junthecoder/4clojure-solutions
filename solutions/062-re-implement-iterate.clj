;; 4Clojure #62 — Re-implement Iterate [Easy]
;; Solved: 2016-12-06

(fn it [f x] (lazy-seq (cons x (it f (f x)))))
