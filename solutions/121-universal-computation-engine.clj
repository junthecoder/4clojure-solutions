;; 4Clojure #121 — Universal Computation Engine [Medium]
;; Solved: 2016-12-16

(fn solve [exp]
  (fn [vars]
    (let [op ((first exp) {'+ + '- - '* * '/ /})
          xs (->> (rest exp)
                  (map #(if (coll? %)
                          ((solve %) vars)
                          (vars % %))))]
      (apply op xs))))
