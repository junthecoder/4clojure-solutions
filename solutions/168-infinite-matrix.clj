;; 4Clojure #168 — Infinite Matrix [Medium]
;; Solved: 2016-12-29

(letfn [(range'
          ([start] (range' start nil))
          ([start end]
           (lazy-seq
             (if (not= start end)
               (cons start (range' (inc start) end))))))
        (infinite-matrix [f i-range j-range]
          (->> i-range
               (map (fn [i] (->> j-range
                                 (map (fn [j] (f i j))))))))]
  (fn
    ([f]         (infinite-matrix f (range' 0) (range' 0)))
    ([f m n]     (infinite-matrix f (range' m) (range' n)))
    ([f m n s t] (infinite-matrix f (range' m (+ m s)) (range' n (+ n t))))))
