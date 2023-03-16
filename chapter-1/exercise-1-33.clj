; Exercise 1.33: You can obtain an even more general version of accumulate
; (Exercise 1.32) by introducing the notion of a filter on the terms to be
; combined. That is, combine only those terms derived from values in the range
; that satisfy a specified condition. The resulting filtered-accumulate
; abstraction takes the same arguments as accumulate, together with an
; additional predicate of one argument that specifies the filter. Write
; filtered-accumulate as a procedure. Show how to express the following using
; filtered-accumulate:
;
;   a. the sum of the squares of the prime numbers in the interval a to b
;      (assuming that you have a prime? predicate already written)
;   b. the product of all the positive integers less than n that
;      are relatively prime to n (i.e., all positive integers i < n
;      such that GCD(i, n) = 1).

(ns sicp.solutions.chapter-1)

(defn filtered-accumulate [combiner filter null-value term a next b]
  (defn iter [a result]
    (let [value (term a)]
      (cond (> a b) result
            (filter a) (iter (next a) (combiner value result))
            :else (iter (next a) result))))
  (iter a null-value))

(defn square [x] (* x x))
(defn divides? [a b] (= (rem b a) 0))
(defn find-divisor [n test-divisor]
  (cond (> (square test-divisor) n) n
        (divides? test-divisor n) test-divisor
        :else (find-divisor n (+ test-divisor 1))))
(defn smallest-divisor [n] (find-divisor n 2))
(defn gcd [a b]
  (if (= b 0)
    a
    (gcd b (rem a b))))
(defn prime? [n]
  (= n (smallest-divisor n)))

(defn sum-squares [a b]
  (filtered-accumulate + prime? 0 square a inc b))

(defn product-relative [n]
  (defn identity [x] x)
  (defn relateve-prime? [i] (= (gcd i n) 1))
  (filtered-accumulate * relateve-prime? 1 identity 1 inc n))

(println (sum-squares 1 24))            ; 1557
(println (product-relative 24))         ; 37182145
