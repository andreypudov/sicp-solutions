; Exercise 1.29: Simpson’s Rule is a more accurate method of numerical
; integration than the method illustrated above. Using Simpson’s Rule, the
; integral of a function f between a and b is approximated as
;
; h
; - (y0 + 4y1 + 2y2 + 4y3 + 2y4 + ... + 2y(n-2) + 4y(n-1) + yn),
; 3
;
; where h = (b-a)/n, for some even integer n, and yk = f (a + kh). (Increasing n
; increases the accuracy of the approximation.) Define a procedure that takes as
; arguments f, a, b, and n and returns the value of the integral, computed
; using Simpson’s Rule. Use your procedure to integrate cube between 0 and 1
; (with n = 100 and n = 1000), and compare the results to those of the integral
; procedure shown above.

(ns sicp.solutions.chapter-1)

(defn cube [x]
  (* x x x))

(defn sum [term a next b]
  (if (> a b)
    0
    (+ (term a)
       (sum term (next a) next b))))

(defn integral [f a b dx]
  (defn add-dx [x]
    (+ x dx))
  (* (sum f (+ a (/ dx 2.0)) add-dx b)
     dx))

(println (integral cube 0 1 0.01))      ; 0.24998750000000042 vs 0.25
(println (integral cube 0 1 0.001))     ; 0.249999875000001   vs 0.25


(defn simpson-integral [f a b n]
  (let [h (/ (- b a) n)]
    (defn y [k] (f (+ a (* k h))))
    (defn add [k] (+ k 2))
    (* (+ (y 0)
          (* 4 (sum y 1 add n))
          (* 2 (sum y 2 add (dec n)))
          (y n))
       (/ h 3))))

(println                                ; 1/4 vs 0.25
  (simpson-integral cube 0 1 100))
(println                                ; 1/4 vs 0.25
  (simpson-integral cube 0 1 1000))
