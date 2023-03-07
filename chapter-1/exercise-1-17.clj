; Exercise 1.17: The exponentiation algorithms in this section are based on
; performing exponentiation by means of repeated multiplication. In a similar
; way, one can perform integer multiplication by means of repeated addition. The
; following multiplication procedure (in which it is assumed that our language
; can only add, not multiply) is analogous to the expt procedure:
;
; (define (* a b)
;   (if (= b 0)
;     0
;     (+ a (* a (- b 1)))))
;
; This algorithm takes a number of steps that is linear in b. Now suppose we
; include, together with addition, operations double, which doubles an integer,
; and halve, which divides an (even) integer by 2. Using these, design a
; multiplication procedure analogous to fast-expt that uses a logarithmic
; number of steps.

(ns sicp.solutions.chapter-1)

(defn * [a b]
  (if (= b 0)
    0
    (+ a (* a (- b 1)))))

(println (* 2 3))                       ; 6
(println (* 2.4 3))                     ; 7.199999999999999 vs  7.2
(println (* 0 2))                       ; 0
; (println (* 2.4 3.6))                 ; stack overflow vs 8.64


(defn double [x] (+ x x))               ; doubles an integer
(defn halve [x] (/ x 2))                ; divides an (even) integer by 2

;                  b         2
; a * b = a x (2 * -) = 2a * - =  a' * b'
;                  2         2
;
; a' <- 2a
; b' <- b/2

(defn fast-* [a b]
  (cond (= b  0) 0
        (even? b) (fast-* (double a) (halve b))
        :else (+ a (fast-* a (- b 1)))))

(println (fast-* 2 3))                   ; 6
(println (fast-* 2.4 3))                 ; 7.199999999999999 vs  7.2
(println (fast-* 0 2))                   ; 0
; (println (fast-* 2.4 3.6))             ; stack overflow vs 8.64
