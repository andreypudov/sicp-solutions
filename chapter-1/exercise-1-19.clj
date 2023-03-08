; Exercise 1.19: There is a clever algorithm for computing the Fibonacci numbers
; in a logarithmic number of steps. Recall the transformation of the state
; variables a and b in the fib-iter process of Section 1.2.2: a <- a + b and
; b <- a. Call this transformation T, and observe that applying T over and over
; again n times, starting with 1 and 0, produces the pair Fib(n + 1) and Fib(n).
; In other words, the Fibonacci numbers are produced by applying T^n ,the n^th
; power of thetransformation T, starting with the pair (1, 0). Now consider T to
; be the special case of p = 0 and q = 1 in a family of transformations Tpq,
; where Tpq transforms the pair (a, b) according to a <- bq + aq + ap and
; b <- bp + aq. Show that if we apply such a transformation Tpq twice, the
; effect is the same as using a single transformation Tp′q′ of the same form,
; and compute p′ and q′ in terms of p and q. This gives us an explicit way to
; square these transformations, and thus we can compute T n using successive
; squaring, as in the fast-expt procedure. Put this all together to complete the
; following procedure, which runs in a logarithmic number of steps:
;
; (define (fib n)
;   (fib-iter 1 0 0 1 n))
; (define (fib-iter a b p q count)
;   (cond ((= count 0) b)
;         ((even? count)
;          (fib-iter a
;                    b
;                    ⟨??⟩ ; compute p′
;                    ⟨??⟩ ; compute q′
;                    (/ count 2)))
;         (else (fib-iter (+ (* b q) (* a q) (* a p))
;                         (+ (* b p) (* a q))
;                         p
;                         q
;                         (- count 1)))))
;
; This exercise was suggested to us by Joe Stoy, based on an example in
; Kaldewaij 1990.

(ns sicp.solutions.chapter-1)

; Tpq(Tpq(a, b)):
; Tpq(a, b) = (bq + aq + ap, bp + aq)
; Tpq(Tpq(a, b)) = ((bp + aq)q + (bq + aq + ap)q + (bq + aq + ap)p, (bp + aq)p + (bq + aq + ap)q)
; Tpq(Tpq(a, b)) = (bpq + aq^2 + bq^2 + aq^2 + apq + bqp + apq + ap^2, bp^2 + apq + bq^2 + aq^2 + apq)
; Tpq(Tpq(a, b)) = (b(2qp + q^2) + a(q^2 + p^2) + a(2qp +q^2), b(p^2 + q^2) + a(2qp + q^2)) = Tp'q'

; Even
; a' <- a
; b' <- b
; p' <- p^2 + q^2
; q' <- 2pq + q^2
;
; Odd
; a' <- bq + aq + ap
; b' <- bp + aq
; p' <- p
; q' <- q

(defn fib-iter [a b p q count]
  (cond (= count 0) b
        (even? count) (fib-iter a
                                b
                                (+ (* p p) (* q q))
                                (+ (* 2 p q) (* q q))
                                (/ count 2))
        :else (fib-iter (+ (* b q) (* a q) (* a p))
                        (+ (* b p) (* a q))
                        p
                        q
                        (- count 1))))

(defn fib [n]
  (fib-iter 1 0 0 1 n))

(println (->> (range 1 10)              ; (1 1 2 3 5 8 13 21 34)
              (map fib)))
