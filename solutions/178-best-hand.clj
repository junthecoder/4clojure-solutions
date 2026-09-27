;; 4Clojure #178 — Best Hand [Hard]
;; Solved: 2016-12-21

(fn [cards]
  (let [suits (sort (map first cards))
        rank (zipmap "23456789TJQKA" (range))
        ranks (sort (map (comp rank second) cards))
        rank-freqs (vals (frequencies ranks))
        in-seq? (fn [xs] (every? identity (map = xs (drop (first xs) (range)))))]
    (cond
     (and (apply = suits)
          (in-seq? ranks)) :straight-flush
     (= 4 (apply max rank-freqs)) :four-of-a-kind
     (= 2 (count rank-freqs)) :full-house
     (apply = suits) :flush
     (in-seq? ranks) :straight
     (and (= 1 (count (filter #{12} ranks)))
          (in-seq? (remove #{12} ranks))) :straight
     (= 3 (apply max rank-freqs)) :three-of-a-kind
     (= 3 (count rank-freqs)) :two-pair
     (= 2 (apply max rank-freqs)) :pair
     :else :high-card)))
