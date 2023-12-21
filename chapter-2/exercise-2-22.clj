; Exercise 2.22: Louis Reasoner tries to rewrite the first square-list procedure
; of Exercise 2.21 so that it evolves an iterative process:
;
; (defn square-list [items]
;   (defn iter [things answer]
;     (if (empty? things)
;       answer
;       (iter (cdr things)
;             (cons (square (car things))
;                   answer))))
;   (iter items nil))
;
; Unfortunately, defining square-list this way produces the answer list in the
; reverse order of the one desired. Why?
;
; Louis then tries to fix his bug by interchanging the arguments to cons:
;
; (defn square-list [items]
;   (defn iter [things answer]
;     (if (empty? things)
;       answer
;       (iter (cdr things)
;             (cons answer
;                   (list (square (car things)))))))
;   (iter items nil))

(ns sicp.solutions.chapter-2)

(defn square [x] (* x x))
(def car first)
(def cdr rest)

(defn square-list1 [items]
  (defn iter [things answer]
    (if (empty? things)
      answer
      (iter (cdr things)
            (cons (square (car things))
                  answer))))
  (iter items nil))

(defn square-list2 [items]
  (defn iter [things answer]
    (if (empty? things)
      answer
      (iter (cdr things)
            (cons answer
                  (list (square (car things)))))))
  (iter items nil))

(println (square-list1 (list 1 2 3 4)))  ; (16 9 4 1)
(println (square-list2 (list 1 2 3 4)))  ; (16 9 4 1)

; In the first procedure the car of items is squared and prepended to answer.
; This causes the list to be reversed.
;
; In the second procedure, although individual elements are appended to answer,
; (car answer) now results in a list instead of a single value and (cdr answer)
; results in square of (car items), that is, answer is nested the wrong way.
