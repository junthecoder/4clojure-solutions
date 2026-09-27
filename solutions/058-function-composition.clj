;; 4Clojure #58 — Function Composition [Medium]
;; Solved: 2016-12-05

(fn [& fs]
  (let [fs (reverse fs)]
    (fn [& args]
      (reduce #(%2 %1) (apply (first fs) args) (rest fs)))))
