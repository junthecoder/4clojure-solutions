;; 4Clojure #70 — Word Sorting [Medium]
;; Solved: 2016-12-06

#(->> (clojure.string/split % #"\W+")
      (sort-by clojure.string/lower-case))
