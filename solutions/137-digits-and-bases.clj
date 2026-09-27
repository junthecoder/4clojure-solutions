;; 4Clojure #137 — Digits and bases [Medium]
;; Solved: 2016-12-10

#(loop [n %1 acc nil]
   (if (zero? n)
     (or acc [0])
     (recur (quot n %2) (cons (mod n %2) acc))))
