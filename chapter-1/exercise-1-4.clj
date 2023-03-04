; Exercise 1.4: Observe that our model of evaluation allows for combinations
; whose operators are compound expressions. Use this observation to describe
; the behavior of the following procedure:
;
; (define (a-plus-abs-b a b)
;   ((if (> b 0) + -) a b))

(ns sicp.solutions.chapter-1)

; The procedure receives two numerical arguments and returns the sum of the
; first argument and absolute value of the second argument.
