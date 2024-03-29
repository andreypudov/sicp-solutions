; Exercise 2.30: Define a procedure square-tree analogous to the square-list
; procedure of Exercise 2.21. That is, square-tree should behave as follows:
;
; (square-tree
;  (list 1
;        (list 2 (list 3 4) 5)
;        (list 6 7)))
; (1 (4 (9 16) 25) (36 49))
;
; Define square-tree both directly (i.e., without using any higher-order
; procedures) and also by using map and recursion.

(ns sicp.solutions.chapter-2)

(defn square-tree1 [tree]
  (cond
    (number? tree) (Math/pow tree 2)
    (empty? tree) '()
    :else (cons (square-tree1 (first tree))
                (square-tree1 (rest tree)))))

(defn square-tree2 [items]
  (map #(if (coll? %)
          (square-tree2 %)
          (Math/pow % 2))
       items))

(println (square-tree1
          (list 1
                (list 2 (list 3 4) 5)
                (list 6 7))))           ; (1 (4 (9 16) 25) (36 49))
(println (square-tree2
          (list 1
                (list 2 (list 3 4) 5)
                (list 6 7))))           ; (1 (4 (9 16) 25) (36 49))
