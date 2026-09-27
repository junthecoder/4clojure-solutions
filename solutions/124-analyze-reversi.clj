;; 4Clojure #124 — Analyze Reversi [Hard]
;; Solved: 2016-12-29

(fn [board color]
  (let [opposite ('{w b, b w} color)
        legal-moves-on-line
        (fn [line]
          (let [line (drop-while (comp not #{'e} :color) line)
                [empties line] (split-with (comp #{'e} :color) line)
                [opposites tail] (split-with (comp #{opposite} :color) line)]
            (if (and (last empties)
                     (not-empty opposites)
                     (= color (:color (first tail))))
              {(:coords (last empties)) (set (map :coords opposites))}
              {})))
        rows (vec (for [y (range 4)]
                    (vec (for [x (range 4)]
                           {:coords [y x], :color (get-in board [y x])}))))
        columns (apply map vector rows)
        diagonal-coords [[[0 0] [1 1] [2 2] [3 3]]
                         [[1 0] [2 1] [3 2]]
                         [[0 1] [1 2] [2 3]]]
        diagonals1 (->> diagonal-coords
                        (map (partial map (partial get-in rows))))
        diagonals2 (->> diagonal-coords
                        (map (partial map (fn [[y x]]
                                            (get-in rows [y (- 3 x)])))))]
    (->> (concat rows columns diagonals1 diagonals2)
         ((fn [lines] (concat lines (map reverse lines))))
         (map legal-moves-on-line)
         (apply merge-with into))))
