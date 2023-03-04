; Exercise 1.3: Define a procedure that takes three numbers as arguments and
; returns the sum of the squares of the two larger numbers.

(ns sicp.solutions.chapter-1
  (:require [clojure.math :as math]))

(defn sum-squares-two-largest [a b c]
  (- (+ (math/pow a 2)
        (math/pow b 2)
        (math/pow c 2))
     (math/pow (min a b c) 2)))

(println (sum-squares-two-largest 1 2 3))
