; Exercise 2.21: The procedure square-list takes a list of numbers as argument
; and returns a list of the squares of those numbers.
;
; (square-list (list 1 2 3 4))
; (1 4 9 16)
;
; Here are two different definitions of square-list. Complete both of them by
; filling in the missing expressions:
;
; (defn square-list [items]
;   (if (null? items)
;     nil
;     (cons ⟨??⟩ ⟨??⟩)))
;
; (defn square-list [items]
;   (map ⟨??⟩ ⟨??⟩))

(defn square-list1 [items]
  (if (empty? items)
    '()
    (cons (Math/pow (first items) 2)
          (square-list1 (rest items)))))

(defn square-list2 [items]
  (map #(Math/pow % 2) items))

(println (square-list1 (list 1 2 3 4))) ; (1 4 9 16)
(println (square-list2 (list 1 2 3 4))) ; (1 4 9 16)
