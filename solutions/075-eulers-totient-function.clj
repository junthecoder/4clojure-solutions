;; 4Clojure #75 — Euler's Totient Function [Medium]
;; Solved: 2016-12-16

(fn [n]
  (if (= n 1)
    1
    (letfn [(gcd [a b] (if (zero? b) a (gcd b (mod a b))))]
      (->> (range 1 n)
           (map #(gcd n %))
           (filter #{1})
           count))))
