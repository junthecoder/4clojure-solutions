;; 4Clojure #59 — Juxtaposition [Medium]
;; Solved: 2016-12-05

(fn [& fs]
  (let [fs (map #(partial apply %) fs)]
    (fn [& args]
      (map #(% args) fs))))
