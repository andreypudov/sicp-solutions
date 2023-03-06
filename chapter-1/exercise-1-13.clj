; Exercise 1.13: Prove that Fib(n) is the closest integer to φ^n/⎷5, where
; φ = (1 + ⎷5)/2. Hint: Let ψ = (1 - ⎷5)/2. Use induction and the definition of
; the Fibonacci numbers (see Section 1.2.2) to prove that
; Fib(n) = (φ^n - ψ^n)/⎷5.

(ns sicp.solutions.chapter-1)

; Fib(n) = Fib(n - 1) + Fib(n - 2)
;
; Insert the following definition on both side:
;
;          φ^n - ψ^n
; Fib(n) = ---------
;              ⎷5
;
; φ^n - ψ^n   φ^(n - 1) - ψ^(n - 1)   φ^(n - 2) - ψ^(n - 2)
; --------- = --------------------- + ---------------------
;     ⎷5                ⎷5                      ⎷5
;
; φ^n - ψ^n = φ^(n - 1) - ψ^(n - 1) + φ^(n - 2) - ψ^(n - 2)
;
;             φ^n   ψ^n   φ^n   ψ^n
; φ^n - ψ^n = --- - --- + --- - ---
;              φ     ψ    φ^2   ψ^2
;
;                   1     1          1     1
; φ^n - ψ^n = φ^n (--- + ---) - ψ^n(--- - ---)
;                   φ    φ^2         ψ    ψ^2
;
; Let's use the followinf definitions:
;
;     1 + ⎷5         1 - ⎷5
; φ = ------ and ψ = ------
;       2              2
;
;                        1                1                       1                1
; φ^n - ψ^n = φ^n (------------ + -----------------) - ψ^n (------------ + -----------------)
;                  (1 + ⎷5) / 2    ((1 + ⎷5) / 2)^2         (1 - ⎷5) / 2    ((1 - ⎷5) / 2)^2
;
;                    2          4                2           4
; φ^n - ψ^n = φ^n (------ + ----------) - ψ^n (------- + ----------)
;                  1 + ⎷5   (1 + ⎷5)^2         1 - ⎷5    (1 - ⎷5)^2
;
;                  2(1 + ⎷5)        4              2(1 - ⎷5)        4
; φ^n - ψ^n = φ^n (---------- + ----------) - ψ^n (---------- + ----------)
;                  (1 + ⎷5)^2   (1 + ⎷5)^2         (1 - ⎷5)^2   (1 - ⎷5)^2
;
;                  2 + 2⎷5 + 4         2 - 2⎷5 + 4
; φ^n - ψ^n = φ^n (-----------) - ψ^n (-----------)
;                  (1 + ⎷5)^2          (1 - ⎷5)^2
;
;                  6 + 2⎷5         6 - 2⎷5
; φ^n - ψ^n = φ^n (-------) - ψ^n (-------)
;                  6 + 2⎷5         6 - 2⎷5
;
; φ^n - ψ^n = φ^n - ψ^n
;
; This proves that
;
;          φ^n - ψ^n
; Fib(n) = ---------
;              ⎷5
;
; Since
;
; -1 < ψ < 0
;
; then
;
; -1 < ψ^n < 1
;
; Also, because
;
; ⎷5 > 2
;
; then
;
;   1     1    ψ^n   1    1
; - - < - -- < --- < -- < -
;   2     ⎷5   ⎷5    ⎷5   2
;
;          φ^n - ψ^n   φ^n   ψ^n
; Fib(n) = --------- = --- - ---
;              ⎷5      ⎷5    ⎷5
;
; φ^n            ψ^n
; --- = Fib(n) + ---
; ⎷5             ⎷5
;
;   1   φ^n   1
; - - < --- < -
;   2   ⎷5    2
;
; So, that
;
;          1            φ^n            1
; Fib(n) - - < Fib(n) + --- < Fib(n) + -
;          2            ⎷5             2
;
; Which means
;
;          1    φ^n            1
; Fib(n) - - <  --- < Fib(n) + -
;          2    ⎷5             2
;
; This proves that
;
; φ^n
; ---
; ⎷5
;
; will never ve further away from the value of Fib(n) than 1, and Fib(n) is
; the closest integer value.
