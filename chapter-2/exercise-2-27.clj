; Exercise 2.27: Modify your reverse procedure of Exercise 2.18 to produce a
; deep-reverse procedure that takes a list as argument and returns as its value
; the list with its elements reversed and with all sublists deep-reversed as
; well.
;
; For example,
;
; (def x (list (list 1 2) (list 3 4)))
; x
; ((1 2) (3 4))
; (reverse x)
; ((3 4) (1 2))
; (deep-reverse x)
; ((4 3) (2 1))

(ns sicp.solutions.chapter-2)

(defn reverse [coll & result]
  (if (empty? coll)
    result
    (recur (rest coll) (cons (first coll) result))))

(defn deep-reverse [coll]
  (if (coll? coll)
    (reverse (map deep-reverse coll))
    coll))

(def x (list (list 1 2) (list 3 4)))
(println x)                             ; ((1 2) (3 4))
(println (reverse x))                   ; ((3 4) (1 2))
(println (deep-reverse x))              ; ((4 3) (2 1))
