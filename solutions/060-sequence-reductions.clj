;; 4Clojure #60 — Sequence Reductions [Medium]
;; Solved: 2016-12-15

(fn r
  ([f acc xs] (cons acc
                    (lazy-seq
                     (if (seq xs)
                       (r f (f acc (first xs)) (rest xs))))))
  ([f xs] (r f (first xs) (rest xs))))
