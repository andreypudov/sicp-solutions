; Exercise 2.5: Show that we can represent pairs of nonnegative integers using
; only numbers and arithmetic operations if we represent the pair a and b as the
; integer that is the product 2^a*3^b. Give the corresponding definitions of the
; procedures cons, car, and cdr.

(ns sicp.solutions.chapter-2)

(defn cons [a b]
  (int (* (Math/pow 2 a)
     (Math/pow 3 b))))
(defn car [p]
  (defn car-iter [p count]
    (if (= 0 (rem p 2))
      (car-iter (/ p 2) (inc count))
      count))
  (car-iter p 0))
(defn cdr [p]
  (defn cdr-iter [p count]
    (if (= 0 (rem p 3))
      (cdr-iter (/ p 3) (inc count))
      count))
  (cdr-iter p 0))

(def a (cons 3 8))

(println (car a))                       ; 3
(println (cdr a))                       ; 8
