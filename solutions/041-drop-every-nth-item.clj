;; 4Clojure #41 — Drop Every Nth Item [Easy]
;; Solved: 2016-12-02

#(apply concat (partition-all (dec %2) %2 %1))
