; Exercise 1.8: Newton’s method for cube roots is based on the fact that if y is
; an approximation to the cube root of x, then a better approximation is given
; by the value
;
; x/y^2 + 2y
; ----------
;     3
;
; Use this formula to implement a cube-root procedure analogous to the
; square-root procedure. (In Section 1.3.4 we will see how to implement
; Newton’s method in general as an abstraction of these square-root and
; cube-root procedures.)

(ns sicp.solutions.chapter-1)

(defn square [x]
  (* x x))

(defn cube [x]
  (* x x x))

(defn average [x y]
  (/ (+ x y) 2))

(defn cube-root [x]
  (defn good-enough? [guess improved-guess]
    (< (abs (- improved-guess guess)) 0.0000000001))
  (defn improve [guess x]
    (/ (+ (/ x (square guess)) (* 2 guess)) 3))
  (defn cube-root-iter [guess x]
    (let [improved-guess (improve guess x)]
      (if (good-enough? guess improved-guess)
        guess
        (cube-root-iter (improve guess x) x))))
  (cube-root-iter 1.0 x))

(println (cube-root 27))                ; 3 vs 3
(println (cube-root 0.00000000123))     ; 0.0010714412696908053 vs 0.001071441269691
(println (cube-root 1234567890123))     ; 10727.659796767139 vs 10727.659796767139093
