; Exercise 1.34: Suppose we define the procedure
;
; (define (f g) (g 2))
;
; Then we have
;
; (f square)
; 4
; (f (lambda (z) (* z (+ z 1))))
; 6
;
; What happens if we (perversely) ask the interpreter to evaluate the
; combination (f f)? Explain.

(ns sicp.solutions.chapter-1)

(defn f [g]
  (g 2))

(defn square [x] (* x x))
(println (f square))                    ; 4

; (f square)
; (square 2)
; (* 2 2)
; 4

(println (f #(* % (+ % 1))))            ; 6

; (f #(* % (+ % 1)))
; (* 2 (+ 2 1))
; (* 2 3)
; 6

; (f f)                                 ; class java.lang.Long cannot be cast to class clojure.lang.IFn

; (f f)
; (f 2)
; (2 2)
