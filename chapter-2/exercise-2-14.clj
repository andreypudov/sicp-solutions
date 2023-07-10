; After considerable work, Alyssa P. Hacker delivers her finished system.
; Several years later, after she has forgotten all about it, she gets a frenzied
; call from an irate user, Lem E. Tweakit. It seems that Lem has noticed that
; the formula for parallel resistors can be written in two algebraically
; equivalent ways:
;
;                                     R1 R2
;                                   ---------
;                                    R1 + R2
;
; and
;
;                                       1
;                                 ------------- .
;                                  1/R1 + 1/R2
;
; He has written the following two programs, each of which computes the
; parallel-resistors formula differently:
;
; (define (par1 r1 r2)
;   (div-interval (mul-interval r1 r2)
;                 (add-interval r1 r2)
;
; (define (par2 r1 r2)
;   (let ((one (make-interval 1 1)))
;     (div-interval
;      one (add-interval (div-interval one r1)
;                        (div-interval one r2)
;
; Lem complains that Alyssa’s program gives different answers for the two ways
; of computing. This is a serious complaint.
;
; Exercise 2.14: Demonstrate that Lem is right. Investigate the behavior of the
; system on a variety of arithmetic expressions. Make some intervals A and B,
; and use them in computing the expressions A/A and A/B. You will get the most
; insight by using intervals whose width is a small percentage of the center
; value. Examine the results of the computation in center-percent form
; (see Exercise 2.12).

(ns sicp.solutions.chapter-2)

(defn make-interval [a b] (list a b))
(defn lower-bound [i] (first i))
(defn upper-bound [i] (second i))

(defn add-interval [x y]
  (make-interval (+ (lower-bound x) (lower-bound y))
                 (+ (upper-bound x) (upper-bound y))))

(defn mul-interval [a b]
  (let [p1 (* (lower-bound a) (lower-bound b))
        p2 (* (lower-bound a) (upper-bound b))
        p3 (* (upper-bound a) (lower-bound b))
        p4 (* (upper-bound a) (upper-bound b))]
    (make-interval (min p1 p2 p3 p4)
                   (max p1 p2 p3 p4))))

(defn div-interval [a b]
  (mul-interval a
                (make-interval (/ 1.0 (upper-bound b))
                               (/ 1.0 (lower-bound b)))))

(defn par1 [r1 r2]
  (div-interval (mul-interval r1 r2)
                (add-interval r1 r2)))

(defn par2 [r1 r2]
  (let [one (make-interval 1 1)]
    (div-interval
      one
      (add-interval (div-interval one r1)
                    (div-interval one r2)))))


(defn make-center-width [c w]
  (make-interval (- c w) (+ c w)))
(defn center [i]
  (/ (+ (lower-bound i) (upper-bound i)) 2))
(defn width [i]
  (/ (- (upper-bound i) (lower-bound i)) 2))
(defn make-center-percent [c p]
  (make-center-width c (* c (/ p 100))))
(defn percent [i]
  (* 100 (/ (width i) (center i))))

(def a (make-center-percent 10 1))
(def b (make-center-percent 20 2))


(defn print-center-percent [i]
  (println (str (center i)
                " ± "
                (percent i)
                "%")))

(println (par1 a b))                    ; (6.361967213114754 6.9844067796610165)
(println (par2 a b))                    ; (6.5776271186440685 6.7554098360655725)

(print-center-percent a)                ; 10 ± 1%
(print-center-percent b)                ; 20 ± 2%

(print-center-percent (par1 a a))       ; 5.002000200020003 ± 2.9992002399280215%
(print-center-percent (par2 a a))       ; 5.0 ± 0.9999999999999963%

(print-center-percent (par1 a b))       ; 6.673186996387885 ± 4.663735385230335%
(print-center-percent (par2 a b))       ; 6.6665184773548205 ± 1.3334000200059877%

(print-center-percent (par1 b b))       ; 10.016006402561025 ± 5.9936076707950345%
(print-center-percent (par2 b b))       ; 10.0 ± 1.9999999999999927%
