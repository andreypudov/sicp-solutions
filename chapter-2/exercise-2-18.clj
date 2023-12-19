; Exercise 2.18: Define a procedure reverse that takes a list as argument and
; returns a list of the same elements in reverse order:
;
; (reverse (list 1 4 9 16 25))
; (25 16 9 4 1)

(ns sicp.solutions.chapter-2)

(defn reverse [list & result]
  (if (empty? list)
    result
    (recur (rest list) (cons (first list) result))))

(def a (list 1 4 9 16 25))
(println (reverse a))                   ; (25 16 9 4 1)
