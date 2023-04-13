; Exercise 2.7: Alyssa’s program is incomplete because she has not specified the
; implementation of the interval abstraction. Here is a definition of the
; interval constructor:
;
; (define (make-interval a b) (cons a b))
;
; Define selectors upper-bound and lower-bound to complete the implementation.

(ns sicp.solutions.chapter-2)

(defn make-interval [a b] (list a b))
(defn lower-bound [i] (first i))
(defn upper-bound [i] (second i))

(def i (make-interval 2 6))

(println (lower-bound i))               ; 2
(println (upper-bound i))               ; 6
