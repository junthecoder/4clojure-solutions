;; 4Clojure #117 — For Science! [Hard]
;; Solved: 2016-12-15

(fn [rows]
  (let [passed (atom #{})
        mouse-pos (first (for [y (range (count rows))
                               x (range (count (first rows)))
                               :when (= \M (get-in rows [y x]))]
                           [y x]))]
    (letfn [(go [pos]
              (if (@passed pos)
                false
                (case (get-in rows pos \#)
                  \C true
                  \# false
                  (let [[y x] pos]
                    (swap! passed conj pos)
                    (or (go [y (inc x)])
                        (go [y (dec x)])
                        (go [(inc y) x])
                        (go [(dec y) x]))))))]
      (go mouse-pos))))
