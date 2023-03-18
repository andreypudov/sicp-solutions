; Exercise 1.35: Show that the golden ratio φ (Section 1.2.2) is a fixed point
; of the transformation x -> 1 + 1/x, and use this fact to compute φ by means
; of the fixed-point procedure.

(ns sicp.solutions.chapter-1)

;
;          1
; x -> 1 + -
;          x
;
;         1
; x = 1 + -
;         x
;
; x^2 = x + 1
;
;
; x^2 - x - 1 = 0
;
;
;     1 + √5
; x = ------
;       2
;
; x is equals to the value of the golden ratio

(def tolerance 0.00001)

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

(println                                ; 1.6180327868852458 vs 1.6180339887
 (fixed-point #(+ 1 (/ 1 %)) 1.0))
