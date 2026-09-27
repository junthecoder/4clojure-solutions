;; 4Clojure #91 — Graph Connectivity [Hard]
;; Solved: 2016-12-28

(fn [edges]
  (let [nodes (->> edges
                   (map (fn [[a b]] (merge {a [b]} {b [a]})))
                   (apply merge-with concat))
        passed (atom #{})]
    (letfn [(go [node-id]
              (when-not (@passed node-id)
                (swap! passed conj node-id)
                (doseq [node-id' (nodes node-id)]
                  (go node-id'))))]
      (go (key (first nodes)))
      (= (count @passed)
         (count nodes)))))
