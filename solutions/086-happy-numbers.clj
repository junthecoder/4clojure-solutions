;; 4Clojure #86 — Happy numbers [Medium]
;; Solved: 2016-12-16

#(letfn [(char->digit [c] (- (int c) 48))
         (square [x] (* x x))]
   (loop [x %
          appeared-nums #{}]
     (cond
      (= x 1) true
      (appeared-nums x) false
      :else (recur (->> (str x)
                        (map (comp square char->digit))
                        (apply +))
                   (conj appeared-nums x)))))
