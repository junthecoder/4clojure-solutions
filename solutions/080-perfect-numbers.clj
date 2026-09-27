;; 4Clojure #80 — Perfect Numbers [Medium]
;; Solved: 2016-12-10

(fn [x]
  (->> (range 1 x)
       (filter #(zero? (mod x %)))
       (apply +)
       (= x)))
