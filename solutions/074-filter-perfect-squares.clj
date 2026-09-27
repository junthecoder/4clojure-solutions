;; 4Clojure #74 — Filter Perfect Squares [Medium]
;; Solved: 2016-12-05

(fn [s]
  (->> (clojure.string/split s #",")
       (filter #(zero? (mod (Math/sqrt (Double. %)) 1)))
       (clojure.string/join ",")))
