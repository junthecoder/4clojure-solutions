;; 4Clojure #132 — Insert between two items [Medium]
;; Solved: 2016-12-06

(fn [f v xs]
  (->> (partition 2 1 xs)
       (mapcat (fn [[a b]] (if (f a b) [v b] [b])))
       (cons (first xs))
       (if (empty? xs) ())))
