;; 4Clojure #131 — Sum Some Set Subsets [Medium]
;; Solved: 2016-12-26

(fn [& ss]
  (letfn [(power-set
           ([s] (power-set (count s) s))
           ([k s]
            (if (zero? k)
              #{#{}}
              (let [subsets (power-set (dec k) s)]
                (into subsets (for [x s
                                    subset subsets]
                                (conj subset x)))))))
          (sums [s]
                (->> (power-set s)
                     (remove empty?)
                     (map (partial apply +))
                          set))]
    (->> ss
         (map sums)
         (apply clojure.set/intersection)
         ((complement empty?)))))
