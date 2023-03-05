; Exercise 1.12: The following pattern of numbers is called Pascal’s triangle.
;
;     1
;    1 1
;   1 2 1
;  1 3 3 1
; 1 4 6 4 1
;   . . .
;
; The numbers at the edge of the triangle are all 1, and each number inside the
; triangle is the sum of the two numbers above it. Write a procedure that
; computes elements of Pascal’s triangle by means of a recursive process.

(ns sicp.solutions.chapter-1)

; 1
; 1 1
; 1 2 1
; 1 3 3 1
; 1 4 6 4 1
;           / 1 if c = 1 or c = r
; f(r, c) = |
;           \ f(r - 1, c - 1) + f (r - 1, c)

(defn pascal-triangle [row column]
  (if (or (= column 1) (= column row))
    1
    (+ (pascal-triangle (dec row) (dec column))
       (pascal-triangle (dec row) column))))

(println (pascal-triangle 1 1))         ; 1

(println (pascal-triangle 2 1))         ; 1
(println (pascal-triangle 2 2))         ; 1

(println (pascal-triangle 3 1))         ; 1
(println (pascal-triangle 3 2))         ; 2
(println (pascal-triangle 3 3))         ; 1

(println (pascal-triangle 4 1))         ; 1
(println (pascal-triangle 4 2))         ; 3
(println (pascal-triangle 4 3))         ; 3
(println (pascal-triangle 4 4))         ; 1
