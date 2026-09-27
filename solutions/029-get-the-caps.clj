;; 4Clojure #29 — Get the Caps [Easy]
;; Solved: 2016-12-01

#(apply str (re-seq #"[A-Z]" %))
