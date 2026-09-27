;; 4Clojure #31 — Pack a Sequence [Easy]
;; Solved: 2016-12-04

#(loop [xs %
        acc ()]
   (if (empty? xs)
     (reverse acc)
     (let [[ys rest] (split-with #{(first xs)} xs)]
       (recur rest (cons ys acc)))))
