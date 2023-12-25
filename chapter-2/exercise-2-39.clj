; Exercise 2.39: Complete the following definitions of reverse (Exercise 2.18)
; in terms of fold-right and fold-left from Exercise 2.38:
; (defn reverse [sequence]
;   (fold-right (fn [x y] ⟨??⟩) '() sequence))
; (defn reverse [sequence]
;   (fold-left (fn [x y] ⟨??⟩) '() sequence))

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

(defn reverse-right [sequence]
  (fold-right (fn [x y] (concat y (list x))) '() sequence))
(defn reverse-left [sequence]
  (fold-left (fn [x y] (cons y x)) '() sequence))

(println (reverse-right '(1 2 3 4)))    ; (4 3 2 1)
(println (reverse-right '(1 2 3 4)))    ; (4 3 2 1)
