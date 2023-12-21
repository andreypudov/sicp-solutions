; Exercise 2.26: Suppose we define x and y to be two lists:
;
; (def x (list 1 2 3))
; (def y (list 4 5 6))
;
; What result is printed by the interpreter in response to evaluating each of
; the following expressions:
;
; (append x y)
; (cons x y)
; (list x y)

(ns sicp.solutions.chapter-2)

(def append concat)

(def x (list 1 2 3))
(def y (list 4 5 6))

(println (append x y))                  ; (1 2 3 4 5 6)
(println (cons x y))                    ; ((1 2 3) 4 5 6)
(println (list x y))                    ; ((1 2 3) (4 5 6))
