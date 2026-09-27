;; 4Clojure #128 — Recognize Playing Cards [Easy]
;; Solved: 2016-12-11

(fn [[s r]]
  {:suit ((zipmap "SHDC" [:spade :heart :diamond :club]) s)
   :rank ((zipmap "23456789TJQKA" (range)) r)})
