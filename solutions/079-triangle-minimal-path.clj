;; 4Clojure #79 — Triangle Minimal Path [Hard]
;; Solved: 2016-12-17

(fn min-path
  ([rows] (min-path rows 0))
  ([rows i] (if-let [[row & rows] rows]
              (+ (row i)
                 (min (min-path rows i)
                      (min-path rows (inc i))))
              0)))
