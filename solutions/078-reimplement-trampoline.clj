;; 4Clojure #78 — Reimplement Trampoline [Medium]
;; Solved: 2016-12-10

(fn t [f & args] (let [r (apply f args)] (if (fn? r) (t r) r)))
