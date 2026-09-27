;; 4Clojure #116 — Prime Sandwich [Medium]
;; Solved: 2016-12-22

(fn [n]
  (let [prime? (fn [n] (and (> n 1)
                            (not-any? #(= 0 (mod n %)) (range 2 n))
                            n))
        next-prime (some prime? (drop (inc n) (range)))
        prev-prime (some prime? (range (dec n) 1 -1))]
    (and (prime? n)
         next-prime
         prev-prime
         (= n (/ (+ next-prime prev-prime) 2)))))
