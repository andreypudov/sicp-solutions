; Exercise 2.8: Using reasoning analogous to Alyssa’s, describe how the
; difference of two intervals may be computed. Define a corresponding
; subtraction procedure, called sub-interval.

(ns sicp.solutions.chapter-2)

(defn make-interval [a b] (list a b))
(defn lower-bound [i] (first i))
(defn upper-bound [i] (second i))

(defn sub-interval [a b]
  (make-interval (- (lower-bound a) (upper-bound b))
                 (- (upper-bound a) (lower-bound b))))

(def a (make-interval 2 6))
(def b (make-interval 1 2))
(def c (sub-interval a b))

(println (lower-bound c))               ; 0
(println (upper-bound c))               ; 5
