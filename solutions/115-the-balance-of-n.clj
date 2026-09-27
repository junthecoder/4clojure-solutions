;; 4Clojure #115 — The Balance of N [Medium]
;; Solved: 2016-12-15

#(let [xs (map int (str %))
       n (/ (count xs) 2)]
   (= (apply + (take n xs))
      (apply + (take-last n xs))))
