; Exercise 1.27: Demonstrate that the Carmichael numbers listed in Footnote 1.47
; really do fool the Fermat test. that is,write a procedure that takes an
; integer n and tests whether an is congruent to a modulo n for every a < n,
; and try your procedure on the given Carmichael numbers.
;
; Numbers that fool the Fermat test are called Carmichael numbers, and little is
; known about them other than that they are extremely rare. There are 255
; Carmichael numbers below 100,000,000. The smallest few are 561, 1105, 1729,
; 2465, 2821, and 6601. In testing primality of very large numbers chosen at
; random, the chance of stumbling upon a value that fools the Fermat test is
; less than the chance that cosmic radiation will cause the computer to make an
; error in carrying out a “correct” algorithm. Considering an algorithm to be
; inadequate for the first reason but not for the second illustrates the
; difference between mathematics and engineering.

(ns sicp.solutions.chapter-1)

(defn square [x]
    (* x x))

(defn expmod [base exp m]
  (cond (= exp 0) 1
        (even? exp) (rem (square (expmod base (/ exp 2) m)) m)
        :else (rem (* base (expmod base (- exp 1) m)) m)))

(defn carmichael-numbers? [n]
  (defn try-it [n a]
    (cond (= a 1) true
          (not= (expmod a n n)) false
          :else (try-it n (- a 1))))
    (try-it n (- n 1)))

; {561 true, 1105 true, 1729 true, 2465 true, 2821 true, 6601 true}
(println (reduce #(assoc %1 %2 (carmichael-numbers? %2))
                 {}
                 '(561 1105 1729 2465 2821 6601)))
