;; 4Clojure #69 — Merge with a Function [Medium]
;; Solved: 2016-12-11

(fn [f & ms]
  (let [ks (distinct (mapcat keys ms))]
    (into {} (map
              (fn [k]
                (->> ms
                     (map #(% k))
                     (remove nil?)
                     (#(if (> (count %) 1) (apply f %) (first %)))
                     (vector k)))
              ks))))
