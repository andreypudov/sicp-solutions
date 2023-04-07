; Exercise 2.3: Implement a representation for rectangles in a plane. (Hint: You
; may want to make use of Exercise 2.2.) In terms of your constructors and
; selectors, create procedures that compute the perimeter and the area of a
; given rectangle. Now implement a different representation for rectangles. Can
; you design your system with suitable abstraction barriers, so that the same
; perimeter and area procedures will work using either representation?

(defn x-point [p] (first p))
(defn y-point [p] (second p))
(defn make-point [x y] (list x y))

(defn start-segment [s] (first s))
(defn end-segment [s] (second s))
(defn make-segment [start end] (list start end))

; make rectangle with the specified location, width and height
(defn rectangle-location [r] (first r))
(defn rectangle-width [r] (second r))
(defn rectangle-height [r] (nth r 2))
(defn make-rectangle [location width height]
  (list location width height))

(defn perimeter [r]
  (* 2 (+ (rectangle-width r)
          (rectangle-height r))))
(defn area [r]
  (* (rectangle-width r)
     (rectangle-height r)))

(def a (make-rectangle (make-point 6 6) 8 6))

(println (perimeter a))                 ; 28
(println (area a))                      ; 48

; make rectangle with the specified location and size
(defn rectangle-location [r] (first r))
(defn rectangle-size [r] (second r))
(defn rectangle-width [r] (first (rectangle-size r)))
(defn rectangle-height [r] (second (rectangle-size r)))
(defn make-rectangle [location size]
  (list location size))

(def b (make-rectangle (make-point 6 6)
                       (make-point 8 6)))

(println (perimeter b))                 ; 28
(println (area b))                      ; 48
