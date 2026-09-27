;; 4Clojure #166 — Comparisons [Easy]
;; Solved: 2016-12-10

#(cond
  (% %2 %3) :lt
  (% %3 %2) :gt
  0 :eq)
