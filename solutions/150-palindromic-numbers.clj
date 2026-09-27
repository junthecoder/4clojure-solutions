;; 4Clojure #150 — Palindromic Numbers [Medium]
;; Solved: 2017-01-02

(fn [start]
  (letfn [(pow [x y]
            (apply * (repeat y (bigint x))))
          (center-digit [n]
            (quot (inc n) 2))
          (left-half [x]
            (-> (str x)
                (subs 0 (center-digit (count (str x))))
                bigint))
          (mirror [n x]
            (bigint (apply str x (->> (str x)
                                      reverse
                                      (drop (mod n 2))))))
          (n-digit-pals
            ([n]
             (if (= n 1)
               (n-digit-pals n 0)
               (n-digit-pals n (pow 10 (dec n)))))
            ([n start]
             (->> (range (left-half start) (pow 10 (center-digit n)))
                  (map #(mirror n %))
                  (drop-while #(< % start)))))]
    (let [n (count (str start))]
      (apply concat
             (n-digit-pals n start)
             (->> (range)
                  (drop (inc n))
                  (map n-digit-pals))))))
