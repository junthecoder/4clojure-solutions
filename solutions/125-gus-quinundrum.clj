;; 4Clojure #125 — Gus' Quinundrum [Hard]
;; Solved: 2017-01-01

(fn [] (let [dq (char 34) sp (char 32)
             ss ["(fn [] (let [dq (char 34) sp (char 32) ss ["
                 "] (str (ss 0) dq (ss 0) dq sp dq (ss 1) dq (char 93) (ss 1))))"]]
         (str (ss 0) dq (ss 0) dq sp dq (ss 1) dq (char 93) (ss 1))))
