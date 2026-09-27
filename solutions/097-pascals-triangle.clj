;; 4Clojure #97 — Pascal's Triangle [Easy]
;; Solved: 2016-12-15

#(loop [i 1 row '(1)]
   (if (< i %)
     (recur (inc i) (cons (* (first row) (/ (- % i) i)) row))
     row))
