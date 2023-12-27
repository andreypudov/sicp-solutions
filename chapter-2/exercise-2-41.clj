; Exercise 2.41: Write a procedure to find all ordered triples of distinct
; positive integers i, j, and k less than or equal to a given integer n that
; sum to a given integer s.

(ns sicp.solutions.chapter-2)

(defn enumerate-interval [low high]
  (if (> low high)
    nil
    (cons low (enumerate-interval (+ low 1) high))))

(defn accumulate [op initial sequence]
  (if (empty? sequence)
    initial
    (op (first sequence)
        (accumulate op initial (rest sequence)))))

(defn flatmap [proc seq]
  (accumulate concat nil (map proc seq)))

(defn unique-triples [n]
  (flatmap (fn [i]
             (flatmap (fn [j]
                        (map (fn [k] (list i j k))
                             (enumerate-interval 1 (- j 1))))
                      (enumerate-interval 1 (- i 1))))
             (enumerate-interval 1 n)))

(println (unique-triples 5))            ; ((3 2 1) (4 2 1) (4 3 1) (4 3 2) (5 2 1) (5 3 1) (5 3 2) (5 4 1) (5 4 2) (5 4 3))
