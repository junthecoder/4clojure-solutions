;; 4Clojure #122 — Read a binary number [Easy]
;; Solved: 2016-12-05

(fn [s] (apply + (map #(if (= %1 \1) %2 0) (reverse s) (iterate #(* % 2) 1))))
