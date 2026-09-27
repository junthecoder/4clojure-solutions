;; 4Clojure #63 — Group a Sequence [Easy]
;; Solved: 2016-12-10

(fn [f xs]
  (reduce
   (fn [m x] (update-in m [(f x)] #(conj (or % []) x)))
   {}
   xs))
