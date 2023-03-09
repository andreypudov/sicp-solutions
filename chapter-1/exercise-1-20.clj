; Exercise 1.20: The process that a procedure generates is of course dependent
; on the rules used by the interpreter. As an example, consider the iterative
; gcd procedure given above. Suppose we were to interpret this procedure using
; normal-order evaluation, as discussed in Section 1.1.5. (The
; normal-order-evaluation rule for if is described in Exercise 1.5.) Using the
; substitution method (for normal order), illustrate the process generated in
; evaluating (gcd 206 40) and indicate the remainder operations that are
; actually performed. How many remainder operations are actually performed in
; the normal-order evaluation of (gcd 206 40)? In the applicative-order
; evaluation?

(ns sicp.solutions.chapter-1)

(defn gcd [a b]
  (if (= b 0)
    a
    (gcd b (rem a b))))

(println (gcd 206 40))

; normal-order evaluation
(defn gcd [206 40]
  (if (= 40 0)
    206
    (gcd 40 (rem 206 40))))

; 1 <- if, 1 <- total
(defn gcd [40 (rem 206 40)]
  (if (= (rem 206 40) 0)
    40
    (gcd (rem 206 40)
         (rem 40 (rem 206 40)))))

; 2 <- if, 3 <- total
(defn gcd [(rem 206 40) (rem 40 (rem 206 40))]
  (if (= (rem 40 (rem 206 40)) 0)
    (rem 206 40)
    (gcd (rem 40 (rem 206 40))
         (rem (rem 206 40) (rem 40 (rem 206 40))))))

; 4 <- if, 7 <- total
(defn gcd [(rem 40 (rem 206 40))
           (rem (rem 206 40) (rem 40 (rem 206 40)))]
  (if (= (rem (rem 206 40) (rem 40 (rem 206 40))) 0)
    (rem 40 (rem 206 40))
    (gcd (rem (rem 206 40) (rem 40 (rem 206 40)))
         (rem (rem 40 (rem 206 40)) (rem (rem 206 40) (rem 40 (rem 206 40)))))))

; 7 <- if, 4 <- first condition, 18 <- total
(defn gcd [(rem (rem 206 40) (rem 40 (rem 206 40)))
           (rem (rem 40 (rem 206 40)) (rem (rem 206 40) (rem 40 (rem 206 40))))]
  (if (= (rem (rem 40 (rem 206 40)) (rem (rem 206 40) (rem 40 (rem 206 40)))) 0)
    (rem (rem 206 40) (rem 40 (rem 206 40)))
    (gcd (rem (rem 40 (rem 206 40)) (rem (rem 206 40) (rem 40 (rem 206 40))))
         (rem (rem (rem 206 40) (rem 40 (rem 206 40))) (rem (rem 40 (rem 206 40)) (rem (rem 206 40) (rem 40 (rem 206 40))))))))

; (rem a b) called 18 times

; applicative-order evaluation
(gcd 206 40)
(gcd 40 6)
(gcd 6 4)
(gcd 4 2)
; 2
;
; (rem a b) called 4 times
