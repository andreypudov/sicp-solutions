; Exercise 1.36: Modify fixed-point so that it prints the sequence of
; approximations it generates, using the newline and display primitives shown in
; Exercise 1.22. Then find a solution to x x = 1000 by finding a fixed point of
; x -> log(1000)/ log(x). (Use Scheme’s primitive log procedure, which computes
; natural logarithms.) Compare the number of steps this takes with and without
; average damping. (Note that you cannot start fixed-point with a guess of 1, as
; this would cause division by log(1) = 0.)

(ns sicp.solutions.chapter-1)

(def tolerance 0.00001)

(defn average [x y]
  (/ (+ x y) 2))

(defn fixed-point [f first-guess]
  (defn close-enough? [v1 v2]
    (< (abs (- v1 v2))
       tolerance))
  (defn try-it [guess]
    (println guess)
    (let [next (f guess)]
      (if (close-enough? guess next)
          next
          (try-it next))))
  (try-it first-guess))

; with average damping
(println                                ; 4.555537551999825
 (fixed-point                           ; 10 steps
  #(average
    %
    (/ (Math/log 1000) (Math/log %)))
  2))

; without average damping
(println                                ; 4.555532270803653
 (fixed-point                           ; 35 steps
  #(/ (Math/log 1000) (Math/log %))
  2))
