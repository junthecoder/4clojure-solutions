;; 4Clojure #98 — Equivalence Classes [Medium]
;; Solved: 2016-12-21

(fn [f s]
  (set (for [a s]
         (set (for [b s :when (= (f a) (f b))] b)))))
