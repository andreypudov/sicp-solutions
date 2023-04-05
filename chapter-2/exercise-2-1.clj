; Exercise 2.1: Define a better version of make-rat that handles both positive
; and negative arguments. make-rat should normalize the sign so that if the
; rational number is positive, both the numerator and denominator are positive,
; and if the rational number is negative, only the numerator is negative.

(ns sicp.solutions.chapter-2)

(defn gcd [a b]
  (if (= b 0)
    a
    (gcd b (rem a b))))

(defn numer [x] (first x))
(defn denom [x] (second x))
(defn make-rat [n d]
  (let [g (gcd n d)]
    (list (/ n g) (/ d g))))

(defn print-rat [x]
  (println (str (numer x)
                "/"
                (denom x))))

(defn add-rat [x y]
  (make-rat (+ (* (numer x) (denom y))
               (* (numer y) (denom x)))
            (* (denom x) (denom y))))

(def one-half (make-rat 1 2))
(def one-third (make-rat 1 3))

(print-rat one-half)                    ; 1/2
(print-rat one-third)                   ; 1/3
(print-rat                              ; 5/6
 (add-rat one-half one-third))

(print-rat (make-rat -1  2))            ; 1/-2
(print-rat (make-rat  1 -2))            ; 1/-2
(print-rat (make-rat -1 -2))            ; 1/2

(defn make-rat [n d]
  (let [g (abs (gcd n d))
        s (if (or (neg-int? n) (neg-int? d)) -1 1)]
    (list (* s (/ (abs n) g)) (/ (abs d) g))))

(print-rat (make-rat -1  2))            ; -1/2
(print-rat (make-rat  1 -2))            ; -1/2
(print-rat (make-rat -1 -2))            ; -1/2
