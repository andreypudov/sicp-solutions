; Exercise 2.35: Redefine count-leaves from Section 2.2.2 as an accumulation:
;
; (defn count-leaves [t]
;   (accumulate ⟨??⟩ ⟨??⟩ (map ⟨??⟩ ⟨??⟩)))

(ns sicp.solutions.chapter-2)

(defn accumulate [op initial sequence]
  (if (empty? sequence)
    initial
    (op (first sequence)
        (accumulate op initial (rest sequence)))))

(defn count-leaves [t]
  (accumulate + 0 (map #(if (coll? %) (count-leaves %) 1) t)))

(def x (cons '(1 2) '(3 4)))
(println (count-leaves x))              ; 4
(println (count-leaves (list x x)))     ; 8
