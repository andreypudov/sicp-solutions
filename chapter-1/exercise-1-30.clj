; Exercise 1.30: The sum procedure above generates a linear recursion. the
; procedure can be rewritten so that the sum is performed iteratively. Show how
; to do this by filling in the missing expressions in the following definition:
;
; (define (sum term a next b)
;   (define (iter a result)
;     (if ⟨??⟩
;       ⟨??⟩
;       (iter ⟨??⟩ ⟨??⟩)))
;   (iter ⟨??⟩ ⟨??⟩))

(ns sicp.solutions.chapter-1)

; recursive procedure
(defn sum [term a next b]
  (if (> a b)
    0
    (+ (term a)
       (sum term (next a) next b))))

; iterative procedure
(defn sum [term a next b]
  (defn iter [a result]
    (if (> a b)
      result
      (iter (next a) (+ (term a) result))))
  (iter a 0))

(defn identity [x] x)
(defn sum-integers [a b]
  (sum identity a inc b))

(println (sum-integers 1 10))           ; 55 vs 55
