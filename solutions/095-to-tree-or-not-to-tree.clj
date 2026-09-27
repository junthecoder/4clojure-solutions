;; 4Clojure #95 — To Tree, or not to Tree [Easy]
;; Solved: 2016-12-10

(fn tree? [x]
    (and (coll? x) (= (count x) 3)
      (let [[_ a b] x]
       (and
        (or (nil? a) (tree? a))
        (or (nil? b) (tree? b))))))
