;; 4Clojure #55 — Count Occurrences [Medium]
;; Solved: 2016-12-05

(fn [xs] (reduce-kv #(assoc %1 %2 (count %3)) {} (group-by identity xs)))
