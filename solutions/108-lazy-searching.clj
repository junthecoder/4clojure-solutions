;; 4Clojure #108 — Lazy Searching [Medium]
;; Solved: 2016-12-21

(fn [& xss]
  (loop [xss xss]
    (if (not-any? empty? xss)
      (if (apply = (map first xss))
        (ffirst xss)
        (let [a (apply max (map first xss))]
          (recur (for [xs xss]
                   (drop-while #(< % a) xs))))))))
