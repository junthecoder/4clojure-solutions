;; 4Clojure #56 — Find Distinct Items [Medium]
;; Solved: 2016-12-05

reduce #(if (some #{%2} %1) %1 (conj %1 %2)) []
