; Exercise 1.42: Let f and д be two one-argument functions. The composition f
; after д is defined to be the function x -> f (д(x)). Define a procedure
; compose that implements composition. For example, if inc is a procedure that
; adds 1 to its argument,
;
; ((compose square inc) 6)
; 49

(ns sicp.solutions.chapter-1)

(defn square [x] (* x x))

(defn compose [f д]
  (fn [x] (f (д x))))

(println ((compose square inc) 6))      ; 49 vs 49
