;; 4Clojure #144 — Oscilrate [Medium]
;; Solved: 2016-12-15

(fn [x & fs] (reductions #(%2 %1) x (cycle fs)))
