;; 4Clojure #82 — Word Chains [Hard]
;; Solved: 2016-12-27

(fn [words]
  (letfn [(remove-first [x coll]
            (let [[pre post] (split-with #(not= x %) coll)]
              (concat pre (rest post))))
          (permutations [xs]
            (if (empty? xs)
              '([])
              (for [x xs
                    :let [perms (permutations (remove-first x xs))]
                    perm perms]
                (cons x perm))))
          (head-match-count [xs ys] (count (take-while identity (map = xs ys))))
          (differ-one-letter? [s1 s2]
            (let [n (head-match-count s1 s2)]
              (case (- (count s2) (count s1))
                0 (= 1 (count (remove identity (map = s1 s2))))
                1 (= (count s1)
                     (+ n (head-match-count (drop n s1) (drop (inc n) s2))))
                -1 (= (count s2)
                      (+ n (head-match-count (drop (inc n) s1) (drop n s2))))
                false)))]
    (->> (permutations words)
         (some #(->> (partition 2 1 %)
                     (every? (partial apply differ-one-letter?))))
         boolean)))
