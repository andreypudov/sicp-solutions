; Exercise 2.13: Show that under the assumption of small percentage tolerances
; there is a simple formula for the approximate percentage tolerance of the
; product of two intervals in terms of the tolerances of the factors. You may
; simplify the problem by assuming that all numbers are positive.

(ns sicp.solutions.chapter-2)

; (a − ϵ^1, a + ϵ^1) * (b − ϵ^2, b + ϵ^2)
; (a + ϵ^1) * (b + ϵ^2) = ab + aϵ^2 + bϵ^1 + ϵ^1ϵ^2
; (a + ϵ^1) * (b − ϵ^2) = ab − aϵ^2 + bϵ^1 − ϵ^1ϵ^2
; (a − ϵ^1) * (b + ϵ^2) = ab + aϵ^2 − bϵ^1 − ϵ^1ϵ^2
; (a − ϵ^1) * (b − ϵ^2) = ab − aϵ^2 − bϵ^1 + ϵ^1ϵ^2
;
; Since a>0 and b>0:
;
; (a + ϵ^1) * (b + ϵ^2) ≈ ab + aϵ^2 + bϵ^1 + ϵ^1ϵ^2
; (a − ϵ^1) * (b − ϵ^2) ≈ ab − aϵ^2 - bϵ^1 + ϵ^1ϵ^2
;
; Assuming that ϵ1 and ϵ2 are small, the quantity ϵ1ϵ2 can be discarded:
;
; (a + ϵ^1) * (b + ϵ^2) ≈ ab + aϵ^2 + bϵ^1
; (a − ϵ^1) * (b − ϵ^2) ≈ ab − aϵ^2 − bϵ^1
;
; Since we are interested in the tolerance terms:
;
; (a + ϵ^1) * (b + ϵ^2) ≈ aϵ^2 + bϵ^1
; (a − ϵ^1) * (b − ϵ^2) ≈ −aϵ^2 − bϵ^1
;
; The new product interval in terms of small tolerance terms looks like this:
;
; [ab − (aϵ^2 + bϵ^1), ab + (aϵ^2 + bϵ^1)]
