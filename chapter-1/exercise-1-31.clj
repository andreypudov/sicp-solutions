; Exercise 1.31:
;
;   a. The sum procedure is only the simplest of a vast number of similar
;      abstractions that can be captured as higher-order procedures. Write an
;      analogous procedure called product that returns the product of the values
;      of a function at points over a given range. Show how to define factorial in
;      terms of product. Also use product to compute approximations to π
;      using the formula
;
;      π   2 * 4 * 4 * 6 * 6 * 8 * * *
;      - = ---------------------------
;      4   3 * 3 * 5 * 5 * 7 * 7 * * *
;
;   b. If your product procedure generates a recursive process, write one that
;      generates an iterative process. If it generates an iterative process,
;      write one that generates a recursive process.

(ns sicp.solutions.chapter-1)

; recursive procedure
(defn product [term a next b]
  (if (> a b)
    1
    (* (term a)
       (product term (next a) next b))))

; iterative procedure
(defn product [term a next b]
  (defn iter [a result]
    (if (> a b)
      result
      (iter (next a) (* (term a) result))))
  (iter a 1))

(defn factorial [n]
  (defn identity [x] x)
  (product identity 1 inc n))

(defn pi [n]
  (defn term [x]
    (if (odd? x)
      (/ (+ 1 x) (+ 2 x))
      (/ (+ 2 x) (+ 1 x))))
  (* 4 (product term 1 inc n)))

(println (factorial 6))                 ; 720 vs 720
(println (double (pi 8192)))            ; 3.141784360234786 vs 3.141592653589793
