;; 4Clojure #84 — Transitive Closure [Hard]
;; Solved: 2016-12-26

(fn [s]
  (let [rels (into {} s)]
    (letfn [(go [k]
                (if-let [v (rels k)]
                  (cons [k v]
                        (->> (go v)
                             (map second)
                             (map #(vector k %))))))]
      (->> (keys rels)
           (mapcat go)
           set))))
