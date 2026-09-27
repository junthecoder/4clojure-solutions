;; 4Clojure #83 — A Half-Truth [Easy]
;; Solved: 2016-12-09

#(if (and (some #{true} %&) (not-every? #{true} %&)) true false)
