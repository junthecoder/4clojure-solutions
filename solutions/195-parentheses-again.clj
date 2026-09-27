;; 4Clojure #195 — Parentheses... Again [Medium]
;; Solved: 2016-12-30

(fn [n]
  (letfn [(go [l r]
            (if (zero? l)
              (list (repeat r ")"))
              (if (<= l r)
                (concat
                  (->> (go (dec l) r)
                       (map #(cons "(" %)))
                  (->> (go l (dec r))
                       (map #(cons ")" %))))
                ())))]
    (->> (go n n)
         (map #(apply str %))
         set)))
