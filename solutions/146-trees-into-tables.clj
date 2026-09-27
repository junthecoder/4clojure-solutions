;; 4Clojure #146 — Trees into tables [Easy]
;; Solved: 2016-12-15

#(into {} (for [[k1 m] %
                [k2 v] m]
            [[k1 k2] v]))
