; Exercise 2.9: The width of an interval is half of the difference between its
; upper and lower bounds. The width is a measure of the uncertainty of the
; number specified by the interval. For some arithmetic operations the width of
; the result of combining two intervals is a function only of the widths of the
; argument intervals, whereas for others the width of the combination is not a
; function of the widths of the argument intervals. Show that the width of the
; sum (or difference) of two intervals is a function only of the widths of the
; intervals being added (or subtracted). Give examples to show that this is not
; true for multiplication or division.

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

(defn div-interval [a b]
  (mul-interval a
                (make-interval (/ 1.0 (upper-bound b))
                               (/ 1.0 (lower-bound b)))))

(defn width-interval [i]
  (/ (- (upper-bound i) (lower-bound i))
     2))

(def a (make-interval 100 101))
(def b (make-interval 22 23))

(println (width-interval a))
(println (width-interval a))
(println (width-interval (mul-interval a b)))
(println (width-interval (div-interval a b)))
