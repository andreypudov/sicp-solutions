; Exercise 2.28: Write a procedure fringe that takes as argument a tree
; (represented as a list) and returns a list whose elements are all the leaves
; of the tree arranged in left-to-right order. For example,
;
; (def x (list (list 1 2) (list 3 4)))
; (fringe x)
; (1 2 3 4)
; (fringe (list x x))
; (1 2 3 4 1 2 3 4)

(ns sicp.solutions.chapter-2)

(defn fringe [coll]
  (cond
    (empty? coll) coll
    (not (coll? (first coll))) coll
    :else (concat (fringe (first coll))
                  (fringe (rest coll)))))

(def x (list (list 1 2) (list 3 4)))
(println (fringe x))                    ; (1 2 3 4)
(println (fringe (list x x)))           ; (1 2 3 4 1 2 3 4)
