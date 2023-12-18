; Exercise 2.17: Define a procedure last-pair that returns the list that
; contains only the last element of a given (nonempty) list:
;
; (last-pair (list 23 72 149 34))
; (34)

(ns sicp.solutions.chapter-2)

(def car first)
(def cdr rest)

(defn last-pair [list]
  (let [rest (cdr list)]
    (if (empty? rest)
      list
      (last-pair rest))))

(def a (list 23 72 149 34))
(println (last-pair a))                 ; 34
