;; 4Clojure #111 — Crossword puzzle [Hard]
;; Solved: 2016-12-27

(fn [word board]
  (let [rows (map #(take-nth 2 %) board)
        cols (apply map vector rows)
        match? (fn [spaces]
                 (and (= (count word) (count spaces))
                      (->> (map vector word spaces)
                           (every? (fn [[c s]] (#{c \_} s))))))]
    (->> (concat rows cols)
         (some (fn [line]
                 (->> line
                      (partition-by #{\#})
                      (take-nth 2)
                      (some match?))))
         boolean)))
