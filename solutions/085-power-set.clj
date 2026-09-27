;; 4Clojure #85 — Power Set [Medium]
;; Solved: 2016-12-21

(fn power-set
  ([s] (power-set (count s) s))
  ([k s]
   (if (= k 0)
     #{#{}}
     (let [subsets (power-set (dec k) s)]
       (into subsets
             (for [x s
                   ss subsets]
               (conj ss x)))))))
