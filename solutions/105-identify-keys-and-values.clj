;; 4Clojure #105 — Identify keys and values [Medium]
;; Solved: 2016-12-15

#(loop [m {} xs %]
   (if (seq xs)
     (let [[k & xs] xs
           [values xs] (split-with (complement keyword?) xs)]
       (recur (assoc m k values) xs))
     m))
