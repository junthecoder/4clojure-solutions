;; 4Clojure #171 — Intervals [Medium]
;; Solved: 2016-12-21

#(loop [acc []
        xs (distinct (sort %))]
   (if (empty? xs)
     acc
     (let [[xs' xs] (->> (range)
                         (drop (first xs))
                         (map vector xs)
                         (split-with (fn [[a b]] (= a b)))
                         (map (partial map first)))
           interval [(first xs') (last xs')]]
       (recur (conj acc interval) xs))))
