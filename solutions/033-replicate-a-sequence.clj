;; 4Clojure #33 — Replicate a Sequence [Easy]
;; Solved: 2016-12-01

(fn [xs n] (mapcat #(repeat n %) xs))
