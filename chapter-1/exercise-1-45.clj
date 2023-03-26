; Exercise 1.45: We saw in Section 1.3.3 that attempting to compute square roots
; by naively finding a fixed point of y -> x/y does not converge, and that this
; can be fixed by average damping. The same method works for finding cube roots
; as fixed points of the average-damped y -> x/y2. Unfortunately, the process
; does not work for fourth roots — a single average damp is not enough to make a
; fixed-point search for y -> x/y3 converge. On the other hand, if we average
; damp twice (i.e., use the average damp of the average damp of y -> x/y3) the
; fixed-point search does converge. Do some experiments to determine how many
; average damps are required to compute nth roots as a fixed-point search based
; upon repeated average damping of y -> x/y^(n-1). Use this to implement a
; simple procedure for computing nth roots using fixed-point, average-damp, and
; the repeated procedure of Exercise 1.43. Assume that any arithmetic operations
; you need are available as primitives.

(ns sicp.solutions.chapter-1)

(def tolerance 0.00001)

(defn average [x y]
  (/ (+ x y) 2))

(defn average-damp [f]
  (fn [x] (average x (f x))))

(defn fixed-point [f first-guess]
  (defn close-enough? [v1 v2]
    (< (abs (- v1 v2))
       tolerance))
  (defn try-it [guess]
    (let [next (f guess)]
      (if (close-enough? guess next)
          next
          (try-it next))))
  (try-it first-guess))

(defn compose [f д]
  (fn [x] (f (д x))))

(defn repeated [f n]
  (defn repeated-iter [n g]
    (if (<= n 1)
      g
      (repeated-iter (dec n) (compose f g))))
  (repeated-iter n f))

(defn power [x n]
  (defn power-iter [y n]
    (if (= n 1)
      y
      (power-iter (* x y) (dec n))))
  (power-iter x n))

(defn nth-root-damped [x nth damp]
  (fixed-point
    ((repeated average-damp damp)
    (fn [y] (/ x (power y (dec nth)))))
   1.0))

(println (nth-root-damped 2 64 6))      ; 1.0108892860517011
; minimal required damp
; 2  => 1
; 4  => 2
; 8  => 3
; 16 => 4
; 32 => 5
; 64 => 6

(defn log2 [n]
  (/ (Math/log n) (Math/log 2)))

(defn nth-root [x nth]
  (fixed-point
    ((repeated average-damp (Math/floor (log2 nth)))
    (fn [y] (/ x (power y (dec nth)))))
   1.0))

(println (nth-root 2 256))              ; 1.0027112765093162
