; Exercise 1.11: A function f is defined by the rule that
;
;         | n if n < 3,
; f (n) = |
;         | f(n - 1) + 2f(n - 2) + 3f(n - 3) if n >= 3
;
; Write a procedure that computes f by means of a recursive process. Write a
; procedure that computes f by means of an iterative process.

(ns sicp.solutions.chapter-1)

; A linear recursive process
(defn f [n]
  (if (< n 3)
    n
    (+ (f (- n 1))
       (* 2 (f (- n 2)))
       (* 3 (f (- n 3))))))

(println (f 0))                         ; 0 -> 0
(println (f 1))                         ; 1 -> 1
(println (f 2))                         ; 2 -> 2
(println (f 3))                         ; 4 -> (+ f(2) 2f(1) 3f(0)) -> (+ 2 2 0) -> 4
(println (f 4))                         ; 11 -> (+ f(3) 2f(2) 3f(1)) -> (+ 4 (* 2 2) (* 3 1)) -> 11
(println (f 5))                         ; 25 -> (+ f(4) 2f(3) 3f(2)) -> (+ 11 (* 2 4) (* 3 2)) -> 25

; A linear iterative process
(defn f [n]
  (defn f-iter [a b c count]
    (if (= count 0)
      a
      (f-iter b c (+ c (* 2 b) (* 3 a)) (dec count))))
  (f-iter 0 1 2 n))

(println (f 0))                           ; 0
(println (f 1))                           ; 1
(println (f 2))                           ; 2
(println (f 3))                           ; 4
(println (f 4))                           ; 11
(println (f 5))                           ; 25
