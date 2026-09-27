;; 4Clojure #140 — Veitch, Please! [Hard]
;; Solved: 2017-01-08

(fn [minterms]
  (letfn [(sort-ignore-case [xs]
            (sort (clojure.string/lower-case (apply str xs))))
          (match? [& terms]
            (apply = (map sort-ignore-case terms)))
          (combinable? [term1 term2]
            (and (not= term1 term2)
                 (match? term1 term2)
                 (= 1 (count (clojure.set/difference term1 term2)))))
          (reduce-terms [terms]
            (let [prime-terms (atom terms)
                  implicants (for [term1 terms
                                   term2 terms
                                   :when (combinable? term1 term2)]
                               (do (swap! prime-terms disj term1)
                                   (clojure.set/intersection term1 term2)))]
              {:implicants (set implicants)
               :prime-terms @prime-terms}))]
    (let [prime-terms
          (loop [acc ()
                 terms minterms]
            (if (empty? terms)
              (->> (map :prime-terms acc)
                   (apply clojure.set/union))
              (let [reduced-terms (reduce-terms terms)]
                (recur (cons reduced-terms acc)
                       (:implicants reduced-terms)))))]
      (-> (for [minterm minterms
                :let [xs (->> prime-terms
                              (filter #(clojure.set/superset? minterm %)))]
                :when (= 1 (count xs))]
            (first xs))
          set))))
