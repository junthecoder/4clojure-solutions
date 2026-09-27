;; 4Clojure #89 — Graph Tour [Hard]
;; Solved: 2016-12-29

(fn [edges]
  (let [nodes (->> edges
                   (map (fn [[a b]] (merge {a #{b}} {b #{a}})))
                   (apply merge-with clojure.set/union))
        passed (atom #{})]
    (letfn [(go [[from to :as edge] n]
              (when-not (@passed (set edge))
                (or (= n (count edges))
                    (do (swap! passed conj (set edge))
                        (some #(go [to %] (inc n)) (nodes to))))))]
      (->> (for [edge edges]
             (do (reset! passed #{})
                 (go edge 1)))
           (some identity)
           boolean))))
