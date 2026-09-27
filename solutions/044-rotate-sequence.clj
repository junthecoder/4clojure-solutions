;; 4Clojure #44 — Rotate Sequence [Medium]
;; Solved: 2016-12-04

#(->> %2
      (split-at (mod %1 (count %2)))
      reverse
      (apply concat))
