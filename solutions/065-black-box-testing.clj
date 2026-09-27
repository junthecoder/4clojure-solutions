;; 4Clojure #65 — Black Box Testing [Medium]
;; Solved: 2016-12-15

#(case ((juxt associative? reversible? ifn?) %)
   [true  true  true ] :vector
   [false false false] :list
   [true  false true ] :map
   [false false true ] :set)
