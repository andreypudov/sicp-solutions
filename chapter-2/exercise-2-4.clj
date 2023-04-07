; Exercise 2.4: Here is an alternative procedural representation of pairs. For
; this representation, verify that (car (cons x y)) yields x for any objects x
; and y.
;
;   (define (cons x y)
;     (lambda (m) (m x y)))
;   (define (car z)
;     (z (lambda (p q) p)))
;
; What is the corresponding definition of cdr? (Hint: To verify that this works,
; make use of the substitution model of Section 1.1.5.)

(ns sicp.solutions.chapter-2)

(defn cons [x y] (fn [m] (m x y)))
(defn car [z] (z (fn [p q] p)))
(defn cdr [z] (z (fn [p q] q)))

; (car (cons x y))
; (car (fn [m] (m x y)))
; ((fn [m] (m x y)) (fn [p q] p))
; ((fn [p q] p) x y)
; x

(def a (cons 1 2))

(println (car a))
(println (cdr a))
