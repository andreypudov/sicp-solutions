; Exercise 2.11: In passing, Ben also cryptically comments: “By testing the
; signs of the endpoints of the intervals, it is possible to break mul-interval
; into nine cases, only one of which requires more than two multiplications.”
; Rewrite this procedure using Ben’s suggestion.
;
; After debugging her program, Alyssa shows it to a potential user, who
; complains that her program solves the wrong problem. He wants a program that
; can deal with numbers represented as a center value and an additive tolerance;
; for example, he wants to work with intervals such as 3.5 +/- 0.15 rather than
; [3.35, 3.65]. Alyssa returns to her desk and fixes this problem by supplying
; an alternate constructor and alternate selectors:
;
; (define (make-center-width c w)
;   (make-interval (- c w) (+ c w)))
; (define (center i)
;   (/ (+ (lower-bound i) (upper-bound i)) 2))
; (define (width i)
;   (/ (- (upper-bound i) (lower-bound i)) 2))
;
; Unfortunately, most of Alyssa’s users are engineers. Real engineering
; situations usually involve measurements with only a small uncertainty,
; measured as the ratio of the width of the interval to the midpoint of the
; interval. Engineers usually specify percentage tolerances on the parameters of
; devices, as in the resistor specifications given earlier.

(ns sicp.solutions.chapter-2)

(defn make-interval [a b] (list a b))
(defn lower-bound [i] (first i))
(defn upper-bound [i] (second i))

(defn mul-interval-old [a b]
  (let [p1 (* (lower-bound a) (lower-bound b))
        p2 (* (lower-bound a) (upper-bound b))
        p3 (* (upper-bound a) (lower-bound b))
        p4 (* (upper-bound a) (upper-bound b))]
    (make-interval (min p1 p2 p3 p4)
                   (max p1 p2 p3 p4))))

(defn mul-interval [a b]
  (defn sign [v] (if (< v 0) :f :t))
  (let [s1 (sign (lower-bound a))
        s2 (sign (upper-bound a))
        s3 (sign (lower-bound b))
        s4 (sign (upper-bound b))
        al (lower-bound a)
        au (upper-bound a)
        bl (lower-bound b)
        bu (upper-bound b)]
    (case (list s1 s2 s3 s4)
      ((:t :t :t :t)) (make-interval (* al bl) (* au bu))
      ((:t :t :f :t)) (make-interval (* au bl) (* au bu))
      ((:t :t :f :f)) (make-interval (* au bl) (* al bu))
      ((:f :t :t :t)) (make-interval (* al bu) (* au bu))
      ((:f :t :f :f)) (make-interval (* au bl) (* al bl))
      ((:f :f :t :t)) (make-interval (* al bu) (* au bl))
      ((:f :f :f :t)) (make-interval (* al bu) (* al bl))
      ((:f :f :f :f)) (make-interval (* au bu) (* al bl))
      (let [p1 (* al bl)
            p2 (* al bu)
            p3 (* au bl)
            p4 (* au bu)]
        (make-interval (min p1 p2 p3 p4)
                       (max p1 p2 p3 p4))))))

(defn validate-mul-interval [a b c d]
  (let [expected (mul-interval-old (make-interval a b) (make-interval c d))
        actual (mul-interval (make-interval a b) (make-interval c d))]
    (if (not= expected actual)
      (println (str "Failed: (" a ", " b "), (" c ", " d ")")))))

(validate-mul-interval +10 +10  +10 +10)
(validate-mul-interval +10 +10  +00 +10)
(validate-mul-interval +10 +10  +00 +00)
(validate-mul-interval +10 +10  +10 -10)
(validate-mul-interval +10 +10  -10 +00)
(validate-mul-interval +10 +10  -10 -10)

(validate-mul-interval +00 +10  +10 +10)
(validate-mul-interval +00 +10  +00 +10)
(validate-mul-interval +00 +10  +00 +00)
(validate-mul-interval +00 +10  +10 -10)
(validate-mul-interval +00 +10  -10 +00)
(validate-mul-interval +00 +10  -10 -10)

(validate-mul-interval +00 +00  +10 +10)
(validate-mul-interval +00 +00  +00 +10)
(validate-mul-interval +00 +00  +00 +00)
(validate-mul-interval +00 +00  +10 -10)
(validate-mul-interval +00 +00  -10 +00)
(validate-mul-interval +00 +00  -10 -10)

(validate-mul-interval +10 -10  +10 +10)
(validate-mul-interval +10 -10  +00 +10)
(validate-mul-interval +10 -10  +00 +00)
(validate-mul-interval +10 -10  +10 -10)
(validate-mul-interval +10 -10  -10 +00)
(validate-mul-interval +10 -10  -10 -10)

(validate-mul-interval -10 +00  +10 +10)
(validate-mul-interval -10 +00  +00 +10)
(validate-mul-interval -10 +00  +00 +00)
(validate-mul-interval -10 +00  +10 -10)
(validate-mul-interval -10 +00  -10 +00)
(validate-mul-interval -10 +00  -10 -10)

(validate-mul-interval -10 -10  +10 +10)
(validate-mul-interval -10 -10  +00 +10)
(validate-mul-interval -10 -10  +00 +00)
(validate-mul-interval -10 -10  +10 -10)
(validate-mul-interval -10 -10  -10 +00)
(validate-mul-interval -10 -10  -10 -10)

(defn make-center-width [c w]
  (make-interval (- c w) (+ c w)))
(defn center [i]
  (/ (+ (lower-bound i) (upper-bound i)) 2))
(defn width [i]
  (/ (- (upper-bound i) (lower-bound i)) 2))

(def a (make-interval 3.5 4.0))
(def b (make-interval 1.2 3.2))
