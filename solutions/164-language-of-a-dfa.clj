;; 4Clojure #164 — Language of a DFA [Hard]
;; Solved: 2017-01-01

(fn [{:keys [start accepts transitions]}]
  (letfn [(go [state acc]
              (lazy-seq
               (concat (->> (transitions state)
                            (filter (fn [[_ state']] (accepts state')))
                            (map (fn [[c _]] (str acc c))))
                       (->> (transitions state)
                            (map (fn [[c state']] (go state' (str acc c))))
                            (apply concat)))))]
    (go start "")))
