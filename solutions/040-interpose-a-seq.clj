;; 4Clojure #40 — Interpose a Seq [Easy]
;; Solved: 2016-12-09

#(rest (interleave (repeat %1) %2))
