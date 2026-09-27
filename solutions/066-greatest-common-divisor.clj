;; 4Clojure #66 — Greatest Common Divisor [Easy]
;; Solved: 2016-12-09

(fn gcd [a b] (if (zero? b) a (gcd b (mod a b))))
