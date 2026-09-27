;; 4Clojure #153 — Pairwise Disjoint Sets [Easy]
;; Solved: 2016-12-21

#(->> (for [s1 %
            s2 (disj % s1)]
        (clojure.set/intersection s1 s2))
      (every? empty?))
