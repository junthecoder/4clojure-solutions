;; 4Clojure #119 — Win at Tic-Tac-Toe [Hard]
;; Solved: 2016-12-15

(fn [sym rows]
  (let [rows (->> rows
                  (map-indexed
                   (fn [y row] (->> row
                                    (map-indexed (fn [x val] [val [y x]]))
                                    vec)))
                  vec)
        columns (apply map vector rows)
        diagonals [(map #(get-in rows [% %]) (range 3))
                   (map #(get-in rows [% (- 2 %)]) (range 3))]]
    (->> (concat rows columns diagonals)
         (filter #(= (sort (map first %)) (sort [:e sym sym])))
         (map #(some (fn [[val coords]] (and (= val :e) coords)) %))
         set)))
