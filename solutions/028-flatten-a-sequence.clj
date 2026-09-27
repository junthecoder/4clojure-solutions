;; 4Clojure #28 — Flatten a Sequence [Easy]
;; Solved: 2016-12-06

(fn f [xs] (mapcat #(if (sequential? %) (f %) [%]) xs))
