;; 4Clojure #100 — Least Common Multiple [Easy]
;; Solved: 2016-12-15

#(letfn [(abs [x] (if (neg? x) (- x) x))
         (gcd [a b] (if (zero? b) a (gcd b (mod a b))))
         (lcm [a b] (/ (abs (* a b)) (gcd a b)))]
   (reduce lcm %&))
