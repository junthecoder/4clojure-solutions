;; 4Clojure #96 — Beauty is Symmetry [Easy]
;; Solved: 2016-12-15

#(letfn [(flip [tree] (if-let [[x l r] tree]
                        [x (flip r) (flip l)]))]
   (= % (flip %)))
