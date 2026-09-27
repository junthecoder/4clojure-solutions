;; 4Clojure #114 — Global take-while [Medium]
;; Solved: 2016-12-16

#(loop [n %1, acc (), xs %3]
   (if (or (<= n 0) (empty? xs))
     (reverse (rest acc))
     (recur (if (%2 (first xs)) (dec n) n)
            (cons (first xs) acc)
            (rest xs))))
