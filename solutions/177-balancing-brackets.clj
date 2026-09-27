;; 4Clojure #177 — Balancing Brackets [Medium]
;; Solved: 2016-12-15

#(let [ops [\[ \( \{]
       cls [\] \) \}]
       pairs (zipmap cls ops)]
   (loop [s % stack ()]
     (if-let [[c & s] s]
       (if ((set ops) c)
         (recur s (cons c stack))
         (if-let [op (pairs c)]
           (and (= op (first stack))
                (recur s (rest stack)))
           (recur s stack)))
       (empty? stack))))
