;; 4Clojure #138 — Squares Squared [Hard]
;; Solved: 2016-12-30

(fn [start end]
  (let [cs (->> (iterate #(* % %) start)
                (take-while #(<= % end))
                (apply str))
        sq-width (int (Math/ceil (Math/sqrt (count cs))))
        sq-area (* sq-width sq-width)
        str-width (dec (* 2 sq-width))
        str-rows (vec (repeat str-width
                              (vec (repeat str-width " "))))
        deltas (for [i (range)
                     d (->> ([[1 1] [-1 1] [-1 -1] [1 -1]] (mod i 4))
                            (repeat (Math/ceil (/ (inc i) 2))))]
                 d)]
    (loop [str-rows str-rows
           cs (take sq-area (concat cs (repeat "*")))
           x (quot str-width 2)
           y (if (odd? sq-width) x (dec x))
           deltas deltas]
      (if (empty? cs)
        (mapv #(apply str %) str-rows)
        (let [[c & cs] cs
              [[dx dy] & deltas] deltas]
          (recur (assoc-in str-rows [y x] c)
                 cs
                 (+ x dx)
                 (+ y dy)
                 deltas))))))
