; Exercise 2.12: Define a constructor make-center-percent that takes a center
; and a percentage tolerance and produces the desired interval. You must also
; define a selector percent that produces the percentage tolerance for a given
; interval. The center selector is the same as the one shown above.

(ns sicp.solutions.chapter-2)

(defn make-interval [a b] (list a b))
(defn lower-bound [i] (first i))
(defn upper-bound [i] (second i))

(defn make-center-width [c w]
  (make-interval (- c w) (+ c w)))
(defn center [i]
  (/ (+ (lower-bound i) (upper-bound i)) 2))
(defn width [i]
  (/ (- (upper-bound i) (lower-bound i)) 2))

(defn make-center-percent [c p]
  (make-center-width c (* c (/ p 100))))
(defn percent [i]
  (* 100 (/ (width i) (center i))))

(def interval (make-center-percent 10 12))
(println interval)                      ; (44/5 56/5)
(println (center interval))             ; 10
(println (percent interval))            ; 12
