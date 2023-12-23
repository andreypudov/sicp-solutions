; Exercise 2.31: Abstract your answer to Exercise 2.30 to produce a procedure
; tree-map with the property that square-tree could be defined as
;
; (defn square-tree [tree] (tree-map square tree))

(ns sicp.solutions.chapter-2)

(defn square [value]
  (Math/pow value 2))

(defn tree-map [f tree]
  (cond
    (number? tree) (f tree)
    (empty? tree) '()
    :else (cons (tree-map f (first tree))
                (tree-map f (rest tree)))))

(defn square-tree [tree]
  (tree-map square tree))

(println (square-tree
          (list 1
                (list 2 (list 3 4) 5)
                (list 6 7))))           ; (1 (4 (9 16) 25) (36 49))
