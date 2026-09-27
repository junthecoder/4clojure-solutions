;; 4Clojure #135 — Infix Calculator [Easy]
;; Solved: 2016-12-05

#(reduce
   (fn [a [op b]] (op a b))
   %1
   (partition 2 %&))
