;; 4Clojure #77 — Anagram Finder [Medium]
;; Solved: 2016-12-05

#(set (for [v (vals (group-by sort %))
            :when (> (count v) 1)]
        (set v)))
