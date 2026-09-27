;; 4Clojure #92 — Read Roman numerals [Hard]
;; Solved: 2016-12-11

#(let [m1 {"IV" 4 "IX" 9 "XL" 40 "XC" 90 "CD" 400 "CM" 900}
       m2 {\I 1 \V 5 \X 10 \L 50 \C 100 \D 500 \M 1000}]
   (loop [s % sum 0]
     (if (seq s)
       (if-let [x (m1 (apply str (take 2 s)))]
         (recur (drop 2 s) (+ sum x))
         (recur (rest s) (+ sum (m2 (first s)))))
       sum)))
