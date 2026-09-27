;; 4Clojure #148 — The Big Divide [Medium]
;; Solved: 2016-12-25

(fn [n a b]
  (let [n (bigint (dec n))]
    (letfn [(abs [x] (if (pos? x) x (- x)))
            (gcd [a b] (if (zero? b) a (gcd b (mod a b))))
            (lcm [a b] (/ (abs (* a b)) (gcd a b)))
            (sum [x] (#(* % (inc %) (/ x 2)) (quot n x)))]
      (+ (sum a)
         (sum b)
         (- (sum (lcm a b)))))))
