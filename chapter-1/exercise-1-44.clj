; Exercise 1.44: The idea of smoothing a function is an important concept in
; signal processing. If f is a function and dx is some small number, then the
; smoothed version of f is the function whose value at a point x is the average
; of f (x - dx), f (x), and f (x + dx). Write a procedure smooth that takes as
; input a procedure that computes f and returns a procedure that computes the
; smoothed f. It is sometimes valuable to repeatedly smooth a function (that is,
; smooth the smoothed function, and so on) to obtain the n-fold smoothed
; function. Show how to generate the n-fold smoothed function of any given
; function using smooth and repeated from Exercise 1.43.

(ns sicp.solutions.chapter-1)

(def dx 0.00001)

(defn compose [f д]
  (fn [x] (f (д x))))

(defn repeated [f n]
  (defn repeated-iter [n g]
    (if (= n 1)
      g
      (repeated-iter (dec n) (compose f g))))
  (repeated-iter n f))

(defn average [a b c]
  (/ (+ a b c) 3))


(defn smooth [f]
  (fn [x] (average (f (- x dx))
                   (f x)
                   (f (+ x dx)))))

(defn n-fold-smooth [f n]
  ((repeated smooth n) f))

(defn square [x] (* x x))

(println                                ; 4.000000000066667
 ((smooth square) 2))

(println                                ; 4.000000000133333
 ((n-fold-smooth square 2) 2))
