; Exercise 2.10: Ben Bitdiddle, an expert systems programmer, looks over
; Alyssa’s shoulder and comments that it is not clear what it means to divide
; by an interval that spans zero. Modify Alyssa’s code to check for this
; condition and to signal an error if it occurs.

(ns sicp.solutions.chapter-2)

(defn make-interval [a b] (list a b))
(defn lower-bound [i] (first i))
(defn upper-bound [i] (second i))

(defn mul-interval [a b]
  (let [p1 (* (lower-bound a) (lower-bound b))
        p2 (* (lower-bound a) (upper-bound b))
        p3 (* (upper-bound a) (lower-bound b))
        p4 (* (upper-bound a) (upper-bound b))]
    (make-interval (min p1 p2 p3 p4)
                   (max p1 p2 p3 p4))))

(defn xor [a b]
  (and (not= a b) (or a b)))

(defn spans-zero-interval? [i]
    (xor (neg? (lower-bound i))
         (neg? (upper-bound i))))

(defn div-interval [a b]
  (if (or (spans-zero-interval? a)
          (spans-zero-interval? b))
    (throw (Exception. "Value cannot span zero."))
    (mul-interval a
                  (make-interval (/ 1.0 (upper-bound b))
                                 (/ 1.0 (lower-bound b))))))

(def a (make-interval 100 101))
(def b (make-interval -22 23))

(println (div-interval a a))            ; (0.9900990099009901 1.01)
; (println (div-interval a b))          ; Value cannot span zero.
