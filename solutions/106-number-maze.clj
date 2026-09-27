;; 4Clojure #106 — Number Maze [Hard]
;; Solved: 2016-12-28

(fn [start goal]
  (let [fs [#(* % 2)
            #(+ % 2)
            #(and (even? %)
                  (/ % 2))]]
    (loop [distance 1
           xs [start]]
      (if (some #{goal} xs)
        distance
        (let [ys (for [x xs
                       f fs
                       :let [y (f x)]
                       :when (number? y)]
                   y)]
          (recur (inc distance) ys))))))
