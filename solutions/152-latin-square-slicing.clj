;; 4Clojure #152 — Latin Square Slicing [Hard]
;; Solved: 2017-01-08

(fn [rows]
  (let [width (apply max (map count rows))]
    (letfn [(row-combinations [row]
              (if (empty? row)
                (list (repeat width nil))
                (for [i (range (inc (- width (count row))))]
                  (concat (repeat i nil)
                          row
                          (repeat (- width (count row) i) nil)))))
            (square-combinations [rows]
              (if (empty? rows)
                '(())
                (for [row (row-combinations (first rows))
                      rest-rows (square-combinations (rest rows))]
                  (cons row rest-rows))))
            (transpose [xss] (apply map list xss))
            (check-lines [lines]
              (let [elems (set (first lines))]
                (and (= (count elems) (count (first lines)))
                     (every? #(= (set %) elems) (rest lines)))))
            (latin-square? [rows] (and (check-lines rows)
                                       (check-lines (transpose rows))))]
      (let [squares (->> (square-combinations rows)
                         (map (fn [sq] (mapv vec sq))))
            num-rows (count (first squares))
            num-cols (count (ffirst squares))
            coords (for [s1 (range 0 (dec num-rows))
                         e1 (range (+ s1 2) (inc num-rows))
                         s2 (range 0 (dec num-cols))
                         :let [e2 (+ s2 (- e1 s1))]
                         :when (<= e2 num-cols)]
                     [[s1 e1] [s2 e2]])
            sub-squares (for [square squares
                              [row-range col-range] (distinct coords)
                              :let [sq (->> (apply subvec square row-range)
                                            (map (fn [row]
                                                   (apply subvec row col-range))))]
                              :when (and (not-any? nil? (flatten sq))
                                         (latin-square? sq))]
                          sq)]
        (->> sub-squares
             distinct
             (map count)
             frequencies)))))
