;; 4Clojure #103 — Generating k-combinations [Medium]
;; Solved: 2016-12-21

(fn combinations [k s]
  (set (if (<= k 1)
         (map #(set [%]) s)
         (for [ss (combinations (dec k) s)
               x s
               :when (not (ss x))]
           (conj ss x)))))
