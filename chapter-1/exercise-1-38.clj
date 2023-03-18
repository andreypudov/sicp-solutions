; Exercise 1.38: In 1737, the Swiss mathematician Leonhard Euler published a
; memoir De Fractionibus Continuis, which included a continued fraction
; expansion for e - 2, where e is the base of the natural logarithms. In this
; fraction, the Ni are all 1, and the Di are successively 1, 2, 1, 1, 4, 1, 1,
; 6, 1, 1, 8, . . .. Write a program that uses your cont-frac procedure from
; Exercise 1.37 to approximate e, based on Euler’s expansion.

(ns sicp.solutions.chapter-1)

(defn cont-frac [n d k]
  (defn iter [i result]
    (if (= 0 i)
      result
      (iter (dec i) (/ (n i) (+ result (d i))))))
  (iter (dec k) (/ (n k) (d k))))

(defn d [n]
  (if (= (rem n 3) 2)
    (* (/ (inc n) 3) 2)
    1))

(println (+ 2                           ; 2.718279569892473 vs 2.71828
            (cont-frac (fn [n] 1.0)
                       d
                       8)))
