;; 4Clojure #101 — Levenshtein Distance [Hard]
;; Solved: 2017-01-01

(fn [xs ys]
  (let [num-rows (inc (count xs))
        num-cols (inc (count ys))
        dist (atom (->> (range num-rows)
                        (map (fn [_] (vec (repeat num-cols 0))))
                        vec))]
    (doseq [i (range num-rows)]
      (swap! dist assoc-in [i 0] i))
    (doseq [j (range num-cols)]
      (swap! dist assoc-in [0 j] j))
    (doseq [j (range 1 num-cols)
            i (range 1 num-rows)
            :let [replace-cost ({true 0 false 1} (= (get xs (dec i))
                                                    (get ys (dec j))))]]
      (swap! dist assoc-in [i j]
             (min (inc (get-in @dist [(dec i) j]))
                  (inc (get-in @dist [i (dec j)]))
                  (+ replace-cost (get-in @dist [(dec i) (dec j)])))))
    (get-in @dist [(dec num-rows) (dec num-cols)])))
