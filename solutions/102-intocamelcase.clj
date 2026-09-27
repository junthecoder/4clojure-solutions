;; 4Clojure #102 — intoCamelCase [Medium]
;; Solved: 2016-12-04

(fn [s] (clojure.string/replace s #"-(.)" #(.toUpperCase (% 1))))
