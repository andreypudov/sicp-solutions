; Exercise 1.9: Each of the following two procedures defines a method for adding
; two positive integers in terms of the procedures inc, which increments its
; argument by 1, and dec, which decrements its argument by 1.
;
; (define (+ a b)
;   (if (= a 0) b (inc (+ (dec a) b))))
; (define (+ a b)
;   (if (= a 0) b (+ (dec a) (inc b))))
;
; Using the substitution model, illustrate the process generated by each
; procedure in evaluating (+ 4 5). Are these processes iterative or recursive?

(ns sicp.solutions.chapter-1)

(define (f a)
  (sum-of-squares (+ a 1) (* a 2)))
(define (sum-of-squares x y)
  (+ (square x) (square y)))
(define (square x) (* x x))

(f 5)
(sum-of-squares (+ a 1) (* a 2))
(sum-of-squares (+ 5 1) (* 5 2))
(+ (square 6) (square 10))
(+ (* 6 6) (* 10 10))
(+ 36 100)
136


(defn + [a b]
  (if (= a 0)
    b
    (inc (+ (dec a) b))))

; A linear recursive process
;
; (+ 4 5)
; (inc (+ (dec 4) 5))
; (inc (inc (+ (dec 3) 5))
; (inc (inc (inc (+ (dec 2) 5))
; (inc (inc (inc (inc (+ (dec 1) 5))
; (inc (inc (inc (inc 5))))
; (inc (inc (inc 6)))
; (inc (inc 7))
; (inc 8)
; 9

(println (+ 4 5))                       ; 9

(defn + [a b]
  (if (= a 0)
    b
    (+ (dec a) (inc b))))

; A linear iterative process
;
; (+ 4 5)
; (+ (dec 4) (inc 5))
; (+ (dec 3) (inc 6))
; (+ (dec 2) (inc 7))
; (+ (dec 1) (inc 8))
; 9

(println (+ 4 5))                       ; 9
