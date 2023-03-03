; Exercise 1.1: Below is a sequence of expressions. What is
; the result printed by the interpreter in response to each
; expression? Assume that the sequence is to be evaluated in
; the order in which it is presented.

(ns sicp.solutions.chapter-1)

(println 10)                                      ; 10
(println (+ 5 3 4))                               ; 12
(println (- 9 1))                                 ; 8
(println (/ 6 2))                                 ; 3
(println (+ (* 2 4) (- 4 6)))                     ; 6
(println (def a 3))
(println (def b (+ a 1)))
(println (+ a b (* a b)))                         ; 19
(println (= a b))                                 ; false
(println (if (and (> b a) (< b (* a b)))          ; 4
           b
           a))

(println (cond (= a 4) 6                          ; 16
               (= b 4) (+ 6 7 a)
               :else 25))

(println (+ 2 (if (> b a) b a)))                  ; 6

(println (* (cond (> a b) a                       ; 16
                  (< a b) b
                  :else -1)
            (+ a 1)))
