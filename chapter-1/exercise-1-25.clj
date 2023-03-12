; Exercise 1.25: Alyssa P. Hacker complains that we went to a lot of extra work
; in writing expmod. After all, she says, since we already know how to compute
; exponentials, we could have simply written
;
; (define (expmod base exp m)
;   (remainder (fast-expt base exp) m))
;
; Is she correct? Would this procedure serve as well for our fast prime tester?
; Explain.

(ns sicp.solutions.chapter-1)

; c = b^exp mod m
; b = 5, exp = 3, m = 13 => c = 5^3 / 13 = 8

(defn square [x]
    (* x x))

(defn expmod [base exp m]
  (cond (= exp 0) 1
        (even? exp) (rem (square (expmod base (/ exp 2) m)) m)
        :else (rem (* base (expmod base (- exp 1) m)) m)))

(println (expmod 2 12 10))              ; 6 <- 2^12 / 10 = 4096 / 10 = 409.6

; (expmod 2 12 10)
; (rem (square (expmod 2 (/ 12 2) 10)) 10)
; (rem (square (rem (square (expmod 2 (/ 6 2) 10)) 10)) 10)
; (rem (square (rem (square (rem (* 2 (expmod 2 (- 3 1) 10)) 10)) 10)) 10)
; (rem (square (rem (square (rem (* 2 (rem (square (expmod 2 (/ 2 2) 10)) 10)) 10)) 10)) 10)
; (rem (square (rem (square (rem (* 2 (rem (square (rem (* 2 (expmod 2 (- 1 1) 10)) 10)) 10)) 10)) 10)) 10)
; (rem (square (rem (square (rem (* 2 (rem (square (rem (* 2 1) 10)) 10)) 10)) 10)) 10)
; (rem (square (rem (square (rem (* 2 (rem (square (rem 2 10)) 10)) 10)) 10)) 10)
; (rem (square (rem (square (rem (* 2 (rem (square 2)  10)) 10)) 10)) 10)
; (rem (square (rem (square (rem (* 2 (rem 4 10)) 10)) 10)) 10)
; (rem (square (rem (square (rem (* 2 4) 10)) 10)) 10)
; (rem (square (rem (square (rem 8 10)) 10)) 10)
; (rem (square (rem (square 8) 10)) 10)
; (rem (square 4) 10)
; (rem 16 10)
; 6


(defn fast-expt [b n]
  (cond (= n 0) 1
        (even? n) (square (fast-expt b (/ n 2)))
        :else (* b (fast-expt b (- n 1)))))

(defn expmod [base exp m]
  (rem (fast-expt base exp) m))

(println (expmod 2 12 10))

; (rem (fast-expt 2 12) 10)
; (rem (square (fast-expt 2 (/ 12 2))) 10)
; (rem (square (square (fast-expt 2 (/ 6 2)))) 10)
; (rem (square (square (* 2 (fast-expt b (- 3 1))))) 10)
; (rem (square (square (* 2 (square (fast-expt 2 (/ 2 2)))))) 10)
; (rem (square (square (* 2 (square (* 2 (fast-expt b (- 1 1))))))) 10)
; (rem (square (square (* 2 (square (* 2 1))))) 10)
; (rem (square (square (* 2 (square 2)))) 10)
; (rem (square (square (* 2 4))) 10)
; (rem (square (square 8)) 10)
; (rem (square 64) 10)
; (rem 4096 10)
; 6

; Alyssa P. Hacker version uses large inermediate results, and as a result,
; requires more memory as well as more computational resources to process big
; numbers.
