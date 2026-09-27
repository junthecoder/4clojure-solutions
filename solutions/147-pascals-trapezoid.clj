;; 4Clojure #147 — Pascal's Trapezoid [Easy]
;; Solved: 2016-12-15

(fn pascal's-trapezoid [xs]
  (lazy-seq
   (cons xs (->> xs
                 (partition-all 2 1)
                 (map #(apply +' %))
                 (cons (first xs))
                 pascal's-trapezoid))))
