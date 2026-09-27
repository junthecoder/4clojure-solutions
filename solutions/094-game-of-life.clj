;; 4Clojure #94 — Game of Life [Hard]
;; Solved: 2016-12-12

(fn [cells]
  (let [w (count cells)
        h (count (first cells))
        live? (fn [x y] (= \# (get-in cells [y x])))
        count-lives (fn [cx cy] (count (for [x [(dec cx) cx (inc cx)]
                                             y [(dec cy) cy (inc cy)]
                                             :when (live? x y)]
                                         1)))
        row (fn [y] (->> (range w)
                         (map (fn [x]
                                (let [num-lives (count-lives x y)]
                                  (if (live? x y)
                                    (if (#{3 4} num-lives) "#" " ")
                                    (if (#{3}   num-lives) "#" " ")))))
                         (apply str)))]
    (map row (range h))))
