; Exercise 2.6: In case representing pairs as procedures wasn’t mind-boggling
; enough, consider that, in a language that can manipulate procedures, we can
; get by without numbers (at least insofar as nonnegative integers are
; concerned) by implementing 0 and the operation of adding 1 as
;
; (define zero (lambda (f) (lambda (x) x)))
; (define (add-1 n)
;   (lambda (f) (lambda (x) (f ((n f) x)))))
;
; This representation is known as Church numerals, after its inventor, Alonzo
; Church, the logician who invented the λ-calculus.
;
; Define one and two directly (not in terms of zero and add-1). (Hint: Use
; substitution to evaluate (add-1 zero)). Give a direct definition of the
; addition procedure + (not in terms of repeated application of add-1).

(ns sicp.solutions.chapter-2)

(def zero (fn [f] (fn [x] x)))
(def one (fn [f] (fn [x] (f x))))
(def two (fn [f] (fn [x] (f (f x)))))

(defn add-1 [n]
  (fn [f] (fn [x] (f ((n f) x)))))
; (add-1 zero)
; (add-1 (fn [f] (fn [x] x)))
; (fn [f] (fn [x] (f (((fn [f] (fn [x] x)) f) x))))
; (fn [f] (fn [x] (f ((fn [x] x) x))))

(defn + [a b]
  (fn [f] (fn [x] ((a f) ((b f) x)))))

(defn print-church [n]
  (println ((n inc) 0)))

(print-church zero)                     ; 0
(print-church one)                      ; 1
(print-church two)                      ; 2
(print-church (+ two one))              ; 3
