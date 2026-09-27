;; 4Clojure #54 — Partition a Sequence [Medium]
;; Solved: 2016-12-05

(fn f [n xs]
  (let [[ys zs] (split-at n xs)]
    (when (>= (count ys) n)
      (cons ys (f n zs)))))
