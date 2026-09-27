;; 4Clojure #112 — Sequs Horribilis [Medium]
;; Solved: 2016-12-29

(fn [n xs]
  (let [sum (atom 0)]
    (letfn [(go [[x & xs]]
              (if-not (nil? x)
                (if (number? x)
                  (do (swap! sum + x)
                      (if (<= @sum n)
                        (cons x (go xs))
                        ()))
                  (cons (go x) (go xs)))))]
      (go xs))))
