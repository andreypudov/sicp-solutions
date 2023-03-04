; Exercise 1.7: The good-enough? test used in computing square roots will not be
; very effective for finding the square roots of very small numbers. Also, in
; real computers, arithmetic operations are almost always performed with limited
; precision. This makes our test inadequate for very large numbers. Explain
; these statements, with examples showing how the test fails for small and large
; numbers. An alternative strategy for implementing good-enough? is to watch how
; guess changes from one iteration to the next and to stop when the change is a
; very small fraction of the guess. Design a square-root procedure that uses
; this kind of end test. Does this work better for small and large numbers?

(ns sicp.solutions.chapter-1)

(defn square [x]
  (* x x))

(defn average [x y]
  (/ (+ x y) 2))

(defn sqrt [x]
  (defn good-enough? [guess x]
    (< (abs (- (square guess) x)) 0.001))
  (defn improve [guess x]
    (average guess (/ x guess)))
  (defn sqrt-iter [guess x]
    (if (good-enough? guess x)
      guess
      (sqrt-iter (improve guess x) x)))
  (sqrt-iter 1.0 x))

; Computation for very small numbers results in inaccurate results because of
; the threshold value in good-enough? function.
(println (sqrt 0.00000000123))          ; 0.031250013107186406 vs 0.000035071355834
(println (square                        ; 0.000976563319199322 vs 0.00000000123
          (sqrt 0.00000000123)))

; Computation for very large numbers could results in stack overflow that happens
; when guessed value is close to the expected value, but the difference is always
; large than threshold value.
(println (sqrt 1234567890123))          ; 1111111.1061109055 vs 1111111.106110905544305
(println (square                        ; 1234567890123 vs 1234567890123
          (sqrt 1234567890123)))
; (println (sqrt 123456789012345))      ; stack overflow

(defn sqrt [x]
  (defn good-enough? [guess improved-guess]
    (< (abs (- improved-guess guess)) 0.0000000001))
  (defn improve [guess x]
    (average guess (/ x guess)))
  (defn sqrt-iter [guess x]
    (let [improved-guess (improve guess x)]
      (if (good-enough? guess improved-guess)
        guess
        (sqrt-iter (improve guess x) x))))
  (sqrt-iter 1.0 x))

(println (sqrt 0.00000000123))          ; 0.00003507135655859831 vs 0.000035071355834
(println (square                        ; 0.0000000012300000508603368 vs 0.00000000123
          (sqrt 0.00000000123)))

(println (sqrt 1234567890123))          ; 1111111.1061109055 vs 1111111.106110905544305
(println (square                        ; 1234567890123 vs 1234567890123
          (sqrt 1234567890123)))
(println (sqrt 123456789012345))        ; 11111111.061111081 vs 11111111.061111080443055
