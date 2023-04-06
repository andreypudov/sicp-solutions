; Exercise 2.2: Consider the problem of representing line segments in a plane.
; Each segment is represented as a pair of points: a starting point and an
; ending point. Define a constructor make-segment and selectors start-segment
; and end-segment that define the representation of segments in terms of points.
; Furthermore, a point can be represented as a pair of numbers: the x coordinate
; and the y coordinate. Accordingly, specify a constructor make-point and
; selectors x-point and y-point that define this representation. Finally, using
; your selectors and constructors, define a procedure midpoint-segment that
; takes a line segment as argument and returns its midpoint (the point whose
; coordinates are the average of the coordinates of the endpoints). To try your
; procedures, you’ll need a way to print points:
;
; (define (print-point p)
;   (newline)
;   (display "(")
;   (display (x-point p))
;   (display ",")
;   (display (y-point p))
;   (display ")"))

(ns sicp.solutions.chapter-2)

(defn x-point [p] (first p))
(defn y-point [p] (second p))
(defn make-point [x y] (list x y))

(defn start-segment [s] (first s))
(defn end-segment [s] (second s))
(defn make-segment [start end] (list start end))

(defn midpoint-segment [s]
  (let [start (start-segment s)
        end (end-segment s)]
    (make-point (/ (+ (x-point start) (x-point end)) 2)
                (/ (+ (y-point start) (y-point end)) 2))))

(defn print-point [p]
  (println (str "("
                (x-point p)
                ","
                (y-point p)
                ")")))

(def a (make-point 3 7))
(def b (make-point 9 1))
(def s (make-segment a b))

(print-point a)                         ; (3,7)
(print-point b)                         ; (9,1)
(print-point (midpoint-segment s))      ; (6,4)
