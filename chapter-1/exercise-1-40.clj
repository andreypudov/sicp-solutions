; Exercise 1.40: Define a procedure cubic that can be used together with the
; newtons-method procedure in expressions of the form
;
; (newtons-method (cubic a b c) 1)
;
; to approximate zeros of the cubic x^3 + ax^2 + bx + c.

(ns sicp.solutions.chapter-1)

(def dx 0.00001)
(def tolerance 0.00001)

(defn cube [x] (* x x x))

(defn square [x] (* x x))

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

(defn deriv [g]
  (fn [x] (/ (- (g (+ x dx)) (g x)) dx)))

(defn newton-transform [g]
  (fn [x] (- x (/ (g x) ((deriv g) x)))))

(defn newtons-method [g guess]
  (fixed-point (newton-transform g) guess))


(defn cubic [a b c]
  (fn [x] (+ (cube x)
             (* a (square x))
             (* b x)
             c)))

(println                                ; 1.0 vs [1, 2, 3]
 (newtons-method (cubic -6 11 -6) 1))
