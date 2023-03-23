; Exercise 1.39: A continued fraction representation of the tangent function was
; published in 1770 by the German mathematician J.H. Lambert:
;
;                x
; tan x = ---------------
;                 x^2
;         1 - -----------
;                   x^2
;             3 - -------
;                 5 - ...
;
; where x is in radians. Define a procedure (tan-cf x k) that computes an
; approximation to the tangent function based on Lambert’s formula. k specifies
; the number of terms to compute, as in Exercise 1.37.

(ns sicp.solutions.chapter-1)

(defn cont-frac [n d k]
  (defn iter [i result]
    (if (= 0 i)
      result
      (iter (dec i) (/ (n i) (+ result (d i))))))
  (iter (dec k) (/ (n k) (d k))))

(defn tan-cf [x k]
  (cont-frac (fn [i] (if (= i 1)
                       x
                       (* x x -1)))
             (fn [i] (dec (* i 2)))
             k))

(println (tan-cf 0.785398 9))           ; 0.9999996732051568 vs 1
(println (Math/tan 0.785398))           ; 0.9999996732051568 vs 1
