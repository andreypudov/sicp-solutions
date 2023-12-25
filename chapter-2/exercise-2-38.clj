; Exercise 2.38: The accumulate procedure is also known as fold-right, because
; it combines the first element of the sequence with the result of combining all
; the elements to the right. There is also a fold-left, which is similar to
; fold-right, except that it combines elements working in the opposite direction:
;
; (defn fold-left [op initial sequence]
;   (defn iter [result rest]
;     (if (empty? rest)
;       result
;       (iter (op result (car rest))
;             (cdr rest))))
;   (iter initial sequence))
;
; What are the values of
;
; (fold-right / 1 (list 1 2 3))
; (fold-left / 1 (list 1 2 3))
; (fold-right list '() (list 1 2 3))
; (fold-left list '() (list 1 2 3))
;
; Give a property that op should satisfy to guarantee that fold-right and
; fold-left will produce the same values for any sequence.

(ns sicp.solutions.chapter-2)

(defn accumulate [op initial sequence]
  (if (empty? sequence)
    initial
    (op (first sequence)
        (accumulate op initial (rest sequence)))))

(def fold-right accumulate)
(def car first)
(def cdr rest)

(defn fold-left [op initial sequence]
  (defn iter [result rest]
    (if (empty? rest)
      result
      (iter (op result (car rest))
            (cdr rest))))
  (iter initial sequence))

(println
 (fold-right / 1 (list 1 2 3)))         ; 3/2
(println
 (fold-left / 1 (list 1 2 3)))          ; 1/6
(println
 (fold-right list '() (list 1 2 3)))    ; (1 (2 (3 ())))
(println
 (fold-left list '() (list 1 2 3)))     ; (((() 3) 2) 1)

; The op should satisfy commutativity to guarantee that fold-right and fold-left
; will produce the same values for any sequence.
