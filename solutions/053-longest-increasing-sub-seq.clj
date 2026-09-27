;; 4Clojure #53 — Longest Increasing Sub-Seq [Hard]
;; Solved: 2016-12-20

(fn [xs]
  (->> xs
       (partition 2 1)
       (partition-by (fn [[a b]] (= (inc a) b)))
       (map (partial mapcat identity))
       (filter (fn [[a b & _]] (= (inc a) b)))
       (map #(cons (first %) %))
       (map (partial take-nth 2))
       (#(if (seq %)
           (apply max-key count %)
           []))))
