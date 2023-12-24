; Exercise 2.34: Evaluating a polynomial in x at a given value of x can be
; formulated as an accumulation. We evaluate the polynomial
;
; a_{n}x^{n} + a_{n - 1}x^{n - 1} + ... + a_{1}x + a_{0}
;
; using a well-known algorithm called Horner’s rule, which structures the
; computation as
;
; (...(a_{n}x + a_{n - 1})x + ... + a_{1})x + a_{0}.
;
; In other words, we start with a_{n}, multiply by x, add a_{n - 1}, multiply by
; x, and so on, until we reach a_{0}.
;
; Fill in the following template to produce a procedure that evaluates a
; polynomial using Horner’s rule. Assume that the coefficients of the polynomial
; are arranged in a sequence, from a0 through an
;
; (defn horner-eval [x coefficient-sequence]
;   (accumulate (fn [this-coeff higher-terms] ⟨??⟩)
;               0
;               coefficient-sequence))
;
; For example, to compute 1 + 3x + 5x^3 + x^5 at x = 2 you would evaluate
;
; (horner-eval 2 (list 1 3 0 5 0 1))

(ns sicp.solutions.chapter-2)

(defn accumulate [op initial sequence]
  (if (empty? sequence)
    initial
    (op (first sequence)
        (accumulate op initial (rest sequence)))))

(defn horner-eval [x coefficient-sequence]
  (accumulate (fn [this-coeff higher-terms]
                (+ this-coeff
                   (* higher-terms x)))
              0
              coefficient-sequence))

(println
 (horner-eval 2 (list 1 3 0 5 0 1)))    ; 79
