;; 4Clojure #141 — Tricky card games [Medium]
;; Solved: 2016-12-25

(fn [trump]
  (fn [cards]
    (let [suits (map :suit cards)
          s (or (some #{trump} suits)
                (first suits))]
      (->> cards
           (filter #(= s (:suit %)))
           (apply max-key :rank)))))
