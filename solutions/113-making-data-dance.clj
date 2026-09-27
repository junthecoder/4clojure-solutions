;; 4Clojure #113 — Making Data Dance [Hard]
;; Solved: 2016-12-29

#(reify clojure.lang.Seqable
   (toString [_] (clojure.string/join ", " (sort %&)))
   (seq [_] (seq (distinct %&))))
