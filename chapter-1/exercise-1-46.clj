; Exercise 1.46: Several of the numerical methods described in this chapter are
; instances of an extremely general computational strategy known as iterative
; improvement. Iterative improvement says that, to compute something, we start
; with an initial guess for the answer, test if the guess is good enough, and
; otherwise improve the guess and continue the process using the improved guess
; as the new guess. Write a procedure iterative-improve that takes two
; procedures as arguments: a method for telling whether a guess is good enough
; and a method for improving a guess. iterative-improve should return as its
; value a procedure that takes a guess as argument and keeps improving the guess
; until it is good enough. Rewrite the sqrt procedure of Section 1.1.7 and the
; fixed-point procedure of Section 1.3.3 in terms of iterative-improve.

(ns sicp.solutions.chapter-1)

(defn square [x]
  (* x x))

(defn average [x y]
  (/ (+ x y) 2))

(defn iterative-improve [good-enough? improve]
  (defn iter [guess]
    (if (good-enough? guess)
      guess
      (iter (improve guess))))
  iter)

(defn sqrt [x]
  (defn good-enough? [guess]
    (< (abs (- (square guess) x)) 0.001))
  (defn improve [guess]
    (average guess (/ x guess)))
  ((iterative-improve good-enough? improve) x))

(println (int (sqrt 4)))                ; 2

(defn fixed-point [f first-guess]
  (defn improve [guess] (f guess))
  (defn good-enough? [guess]
    (< (abs (- (improve guess) guess)) 0.00001))
  ((iterative-improve good-enough? improve) first-guess))

(println                                ; 0.7390893414033928
 (fixed-point #(Math/cos %) 1.0))
