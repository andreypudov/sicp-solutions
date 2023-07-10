; Exercise 2.16: Explain, in general, why equivalent algebraic expressions may
; lead to different answers. Can you devise an interval-arithmetic package that
; does not have this shortcoming, or is this task impossible? (Warning: this
; problem is very difficult.

(ns sicp.solutions.chapter-2)

; Wikipedia
;
; Dependency problem
;
; The so-called "dependency" problem is a major obstacle to the application of
; interval arithmetic. Although interval methods can determine the range of
; elementary arithmetic operations and functions very accurately, this is not
; always true with more complicated functions. If an interval occurs several
; times in a calculation using parameters, and each occurrence is taken
; independently, then this can lead to an unwanted expansion of the resulting
; intervals.
;
; ...
;
; In general, it can be shown that the exact range of values can be achieved, if
; each variable appears only once and if f is continuous inside the box.
; However, not every function can be rewritten this way. intervals.
;
; https://en.wikipedia.org/wiki/Interval_arithmetic
;
; Summary: it is impossible to devise an interval-arithmetic package that does
; not have this shortcoming.
