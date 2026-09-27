;; 4Clojure #88 — Symmetric Difference [Easy]
;; Solved: 2016-12-05

#(clojure.set/union
   (clojure.set/difference %1 %2)
   (clojure.set/difference %2 %1))
