;; 4Clojure #73 — Analyze a Tic-Tac-Toe Board [Hard]
;; Solved: 2016-12-15

(fn [rows]
  (let [columns (apply map vector rows)
        diagonals [[(get-in rows [0 0])
                    (get-in rows [1 1])
                    (get-in rows [2 2])]
                   [(get-in rows [0 2])
                    (get-in rows [1 1])
                    (get-in rows [2 0])]]]
    (some #(if (and
                (not= :e (first %))
                (apply = %))
             (first %))
          (concat rows columns diagonals))))
