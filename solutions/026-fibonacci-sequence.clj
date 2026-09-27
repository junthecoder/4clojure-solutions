;; 4Clojure #26 — Fibonacci Sequence [Easy]
;; Solved: 2016-12-05

#(loop [xs () a 0 b 1 n %]
   (if (zero? n)
     (reverse xs)
     (recur (cons b xs) b (+ a b) (dec n))))
