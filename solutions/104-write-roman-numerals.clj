;; 4Clojure #104 — Write Roman Numerals [Medium]
;; Solved: 2016-12-15

#(loop [n % s "" nums [[1000 "M"] [900 "CM"] [500 "D"] [400 "CD"] [100 "C"] [90 "XC"] [50 "L"] [40 "XL"] [10 "X"] [9 "IX"] [5 "V"] [4 "IV"] [1 "I"]]]
   (if-let [[[val sym] & nums'] nums]
     (if (>= n val)
       (recur (- n val) (str s sym) nums)
       (recur n s nums'))
     s))
