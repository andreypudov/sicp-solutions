; Exercise 2.33: Fill in the missing expressions to complete the following
; definitions of some basic list-manipulation operations as accumulations:
;
; (defn map [p sequence]
;   (accumulate #(⟨??⟩) '() sequence))
; (defn append [seq1 seq2]
;   (accumulate cons ⟨??⟩ ⟨??⟩))
; (defn length [sequence]
;   (accumulate ⟨??⟩ 0 sequence))

(ns sicp.solutions.chapter-2)

(defn accumulate [op initial sequence]
  (if (empty? sequence)
    initial
    (op (first sequence)
        (accumulate op initial (rest sequence)))))

(println
 (accumulate + 0 (list 1 2 3 4 5)))     ; 15
(println
 (accumulate * 1 (list 1 2 3 4 5)))     ; 120
(println
 (accumulate
  cons
  '()
  (list 1 2 3 4 5)))                    ; (1 2 3 4 5)

(defn map [p sequence]
  (accumulate #(cons (p %1) %2) '() sequence))
(defn append [seq1 seq2]
  (accumulate cons seq2 seq1))
(defn length [sequence]
 (accumulate #(inc %2) 0 sequence))

(println (map inc '(1 2 3 4)))          ; (2 3 4 5)
(println (append '(1 2) '(3 4)))        ; (1 2 3 4)
(println (length '(1 2 3 4)))           ; 4
