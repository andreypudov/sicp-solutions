; Exercise 1.18: Using the results of Exercise 1.16 and Exercise 1.17, devise a
; procedure that generates an iterative process for multiplying two integers in
; terms of adding, doubling, and halving and uses a logarithmic number of steps.
;
; This algorithm, which is sometimes known as the “Russian peasant method” of
; multiplication, is ancient. Examples of its use are found in the Rhind
; Papyrus, one of the two oldest mathematical documents in existence, written
; about 1700 B.C. (and copied from an even older document) by an Egyptian scribe
; named A’h-mose.

(ns sicp.solutions.chapter-1)

(defn double [x] (+ x x))               ; doubles an integer
(defn halve [x] (/ x 2))                ; divides an (even) integer by 2

; Even:
;                  b         b
; a * b = a x (2 * -) = 2a * - = a' * b'
;                  2         2
; n' = n
; a' = 2a
; b' = b / 2
;
; Odd:
; n + a * b = n + a x (1 + (b - 1)) = (n + a) + a * (b - 1) = n + a' + b'
; n' = n + a
; a' = a
; b' = b - 1

(defn * [a b]
  (defn *-iter [n a b]
    (cond (= b  0) n
          (even? b) (*-iter n (double a) (halve b))
          :else (*-iter (+ n a) a (- b 1))))
  (*-iter 0 a b))

(println (* 2 3))                       ; 6
(println (* 2.4 3))                     ; 7.199999999999999 vs  7.2
(println (* 0 2))                       ; 0
; (println (* 2.4 3.6))                 ; stack overflow vs 8.64
