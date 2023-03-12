; Exercise 1.26: Louis Reasoner is having great difficulty doing Exercise 1.24.
; His fast-prime? test seems to run more slowly than his prime? test. Louis
; calls his friend Eva Lu Ator over to help. When they examine Louis’s code,
; they find that he has rewritten the expmod procedure to use an explicit
; multiplication, rather than calling square:
;
; (define (expmod base exp m)
;   (cond ((= exp 0) 1)
;         ((even? exp)
;          (remainder (* (expmod base (/ exp 2) m)
;                        (expmod base (/ exp 2) m))
;                     m))
;         (else
;          (remainder (* base
;                        (expmod base (- exp 1) m))
;                     m))))
;
; “I don’t see what difference that could make,” says Louis. “I do.” says Eva.
; “By writing the procedure like that, you have transformed the Θ(log n) process
; into a Θ(n) process.” Explain.

(ns sicp.solutions.chapter-1)

(defn square [x]
    (* x x))

(defn expmod [base exp m]
  (println "expmod" base exp m)
  (cond (= exp 0) 1

        (even? exp)
        (rem (square (expmod base (/ exp 2) m))
             m)

        :else
        (rem (* base (expmod base (- exp 1) m))
             m)))

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
;
; expmod invoked 6 times - Θ(log n) process


(defn expmod [base exp m]
  (println "expmod2" base exp m)
  (cond (= exp 0) 1

        (even? exp)
        (rem (* (expmod base (/ exp 2) m)
                (expmod base (/ exp 2) m))
             m)
        :else
        (rem (* base
                (expmod base (- exp 1) m))
             m)))

(println (expmod 2 12 10))

; (expmod 2 12 10)
; (rem (* (expmod 2 (/ 12 2) 10)
;         (expmod 2 (/ 12 2) 10))
;      10)
;
; (rem (* (rem (* (expmod 2 (/ 6 2) 10)
;                 (expmod 2 (/ 6 2) 10))
;              10)
;         (rem (* (expmod 2 (/ 6 2) 10)
;                 (expmod 2 (/ 6 2) 10))
;              10))
;      10)
;
; (rem (* (rem (* (rem (* 2 (expmod 2 (- 3 1) 10))
;                      10)
;                 (rem (* 2 (expmod 2 (- 3 1) 10))
;                      10))
;              10)
;         (rem (* (rem (* 2 (expmod 2 (- 3 1) 10))
;                      10)
;                 (rem (* 2 (expmod 2 (- 3 1) 10))
;                      10))
;              10))
;      10)
;
; (rem (* (rem (* (rem (* 2 (rem (* (expmod 2 (/ 2 2) 10)
;                                   (expmod 2 (/ 2 2) 10))
;                                10))
;                      10)
;                 (rem (* 2 (rem (* (expmod 2 (/ 2 2) 10)
;                                   (expmod 2 (/ 2 2) 10))
;                                10))
;                      10))
;              10)
;         (rem (* (rem (* 2 (rem (* (expmod 2 (/ 2 2) 10)
;                                   (expmod 2 (/ 2 2) 10))
;                                10))
;                      10)
;                 (rem (* 2 (rem (* (expmod 2 (/ 2 2) 10)
;                                   (expmod 2 (/ 2 2) 10))
;                                10))
;                      10))
;              10))
;      10)
;
; (rem (* (rem (* (rem (* 2 (rem (* (rem (* 2 (expmod 2 (- 1 1) 10)) 10)
;                                   (rem (* 2 (expmod 2 (- 1 1) 10)) 10))
;                                10))
;                      10)
;                 (rem (* 2 (rem (* (rem (* 2 (expmod 2 (- 1 1) 10)) 10)
;                                   (rem (* 2 (expmod 2 (- 1 1) 10)) 10))
;                                10))
;                      10))
;              10)
;         (rem (* (rem (* 2 (rem (* (rem (* 2 (expmod 2 (- 1 1) 10)) 10)
;                                   (rem (* 2 (expmod 2 (- 1 1) 10)) 10))
;                                10))
;                      10)
;                 (rem (* 2 (rem (* (rem (* 2 (expmod 2 (- 1 1) 10)) 10)
;                                   (rem (* 2 (expmod 2 (- 1 1) 10)) 10))
;                                10))
;                      10))
;              10))
;      10)
;
; (rem (* (rem (* (rem (* 2 (rem (* (rem (* 2 1) 10)
;                                   (rem (* 2 1) 10))
;                                10))
;                      10)
;                 (rem (* 2 (rem (* (rem (* 2 1) 10)
;                                   (rem (* 2 1) 10))
;                                10))
;                      10))
;              10)
;         (rem (* (rem (* 2 (rem (* (rem (* 2 1) 10)
;                                   (rem (* 2 1) 10))
;                                10))
;                      10)
;                 (rem (* 2 (rem (* (rem (* 2 1) 10)
;                                   (rem (* 2 1) 10))
;                                10))
;                      10))
;              10))
;      10)
;
; (rem (* (rem (* (rem (* 2 (rem (* (rem 2 10)
;                                   (rem 2 10))
;                                10))
;                      10)
;                 (rem (* 2 (rem (* (rem 2 10)
;                                   (rem 2 10))
;                                10))
;                      10))
;              10)
;         (rem (* (rem (* 2 (rem (* (rem 2 10)
;                                   (rem 2 10))
;                                10))
;                      10)
;                 (rem (* 2 (rem (* (rem 2 10)
;                                   (rem 2 10))
;                                10))
;                      10))
;              10))
;      10)
;
; (rem (* (rem (* (rem (* 2 (rem (* 2 2) 10))
;                      10)
;                 (rem (* 2 (rem (* 2 2) 10))
;                      10))
;              10)
;         (rem (* (rem (* 2 (rem (* 2 2) 10))
;                      10)
;                 (rem (* 2 (rem (* 2 2) 10))
;                      10))
;              10))
;      10)
;
; (rem (* (rem (* (rem (* 2 (rem 4 10))
;                      10)
;                 (rem (* 2 (rem 4 10))
;                      10))
;              10)
;         (rem (* (rem (* 2 (rem 4 10))
;                      10)
;                 (rem (* 2 (rem 4 10))
;                      10))
;              10))
;      10)
;
; (rem (* (rem (* (rem (* 2 4) 10)
;                 (rem (* 2 4) 10))
;              10)
;         (rem (* (rem (* 2 4) 10)
;                 (rem (* 2 4) 10))
;              10))
;      10)
;
; (rem (* (rem (* (rem 8 10)
;                 (rem 8 10))
;              10)
;         (rem (* (rem 8 10)
;                 (rem 8 10))
;              10))
;      10)
;
; (rem (* (rem (* 8 8) 10)
;         (rem (* 8 8) 10))
;      10)
;
; (rem (* (rem 64 10)
;         (rem 64 10))
;      10)
;
; (rem (* 4 4) 10)
;
; (rem 16 10)
;
; 6
;
; expmod invoked 27 times - Θ(n) process
