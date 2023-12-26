; Exercise 2.40: Define a procedure unique-pairs that, given an integer n,
; generates the sequence of pairs (i, j) with 1 <= j < i <= n. Use unique-pairs
; to simplify the definition of prime-sum-pairs given above.

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

; (defn unique-pairs [n]
;   (accumulate
;    concat '() (map (fn [i]
;                      (map (fn [j] (list i j))
;                           (enumerate-interval 1 (- i 1))))
;                    (enumerate-interval 1 n))))

(defn unique-pairs [n]
  (flatmap (fn [i]
             (map (fn [j] (list i j))
                       (enumerate-interval 1 (- i 1))))
                (enumerate-interval 1 n)))

(defn random [n]
  (int (rand n)))

(defn square [x]
    (* x x))

(defn expmod [base exp m]
  (cond (= exp 0) 1
        (even? exp) (rem (square (expmod base (/ exp 2) m)) m)
        :else (rem (* base (expmod base (- exp 1) m)) m)))

(defn fermat-test [n]
  (defn try-it [a]
    (= (expmod a n n) a))
  (try-it (+ 1 (random (- n 1)))))

(defn prime? [n times]
  (cond (= times 0) true
        (fermat-test n) (prime? n (- times 1))
        :else false))

(defn prime-sum? [pair]
  (prime? (+ (first pair) (second pair)) 100))

(defn make-pair-sum [pair]
  (list (first pair) (second pair) (+ (first pair) (second pair))))

(defn prime-sum-pairs [n]
  (map make-pair-sum (filter prime-sum? (unique-pairs n))))

(println (unique-pairs 5))              ; ((2 1) (3 1) (3 2) (4 1) (4 2) (4 3) (5 1) (5 2) (5 3) (5 4))
(println (prime-sum-pairs 5))           ; ((2 1 3) (3 2 5) (4 1 5) (4 3 7) (5 2 7))
