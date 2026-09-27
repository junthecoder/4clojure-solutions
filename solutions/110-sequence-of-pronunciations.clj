;; 4Clojure #110 — Sequence of pronunciations [Medium]
;; Solved: 2016-12-15

(fn pros [xs]
  (lazy-seq
   (let [xs (->> xs
                 (partition-by identity)
                 (mapcat (juxt count first)))]
     (cons xs (pros xs)))))
