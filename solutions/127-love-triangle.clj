;; 4Clojure #127 — Love Triangle [Hard]
;; Solved: 2017-01-07

(fn [rows]
  (letfn [(->bits [n]
            (->> (iterate #(quot % 2) n)
                 (take-while pos?)
                 (map #(mod % 2))
                 reverse))
          (pad-left [x w xs]
            (concat (repeat (- w (count xs)) x) xs))
          (transpose [xss]
            (apply map list xss))
          (rotate-cw [xss]
            (map reverse (transpose xss)))
          (fit-triangles [square]
            (let [h (count square)
                  w (count (first square))]
              (cond
                (= w h)
                (for [square' (take 4 (iterate #(rotate-cw %) square))]
                  (map-indexed #(take (inc %1) %2) square'))

                (= w (dec (* 2 h)))
                (for [square' [square (reverse square)]]
                  (map-indexed #(drop %1 (drop-last %1 %2)) square'))

                :else nil)))
          (go [rows]
            (let [h (count rows)
                  w (count (first rows))]
              (when (and (>= h 2) (>= w 2))
                (let [triangles (->> (fit-triangles rows)
                                     (remove #(some zero? (flatten %))))
                      areas (map (comp count flatten) triangles)
                      cols (transpose rows)
                      sub-squares [(map rest rows) (map butlast rows)
                                   (map rest cols) (map butlast cols)]
                      sub-areas (keep #(go %) sub-squares)
                      areas (concat areas sub-areas)]
                  (and (seq areas)
                       (apply max areas))))))]
    (let [rows (map #(->bits %) rows)
          max-width (apply max (map count rows))
          rows (map #(pad-left 0 max-width %) rows)]
      (go rows))))
