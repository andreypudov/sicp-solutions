; Exercise 1.23: The smallest-divisor procedure shown at the start of this
; section does lots of needless testing: After it checks to see if the number is
; divisible by 2 there is no point in checking to see if it is divisible by any
; larger even numbers. This suggests that the values used for test-divisor
; should not be 2, 3, 4, 5, 6, . . ., but rather 2, 3, 5, 7, 9, . . ..
; To implement this change, define a procedure next that returns 3 if its input
; is equal to 2 and otherwise returns its input plus 2. Modify the
; smallest-divisor procedure to use (next test-divisor) instead of
; (+ test-divisor 1). With timed-prime-test incorporating this modified version
; of smallest-divisor, run the test for each of the 12 primes found in
; Exercise 1.22. Since this modification halves the number of test steps, you
; should expect it to run about twice as fast. Is this expectation confirmed?
; If not, what is the observed ratio of the speeds of the two algorithms, and
; how do you explain the fact that it is different from 2?

(ns sicp.solutions.chapter-1)

(defn runtime []
  (System/nanoTime))

(defn smallest-divisor [n]
  (defn square [x]
    (* x x))
  (defn divides? [a b]
    (= (rem b a) 0))
  (defn next [n]
    (if (= n 2)
      3
      (+ n 2)))
  (defn find-divisor [n test-divisor]
    (cond (> (square test-divisor) n) n
          (divides? test-divisor n) test-divisor
          :else (find-divisor n (next test-divisor))))
  (find-divisor n 2))

(defn prime? [n]
  (= n (smallest-divisor n)))

(defn report-prime [elapsed-time]
  (print " *** ")
  (print elapsed-time))

(defn start-prime-test [n start-time]
  (if (prime? n)
    (report-prime (- (runtime) start-time))))

(defn timed-prime-test [n]
  (newline)
  (print n)
  (start-prime-test n (runtime)))

(defn search-range [start end]
  (filter odd? (range start end)))

(defn search-for-primes [start end]
  (run! timed-prime-test (search-range start end)))

(search-for-primes 1000 1020)           ; 1009, 1013, 1019          : ~  25 000 ns <  40 000 ns
(search-for-primes 10000 10038)         ; 10007, 10009, 10037       : ~  60 000 ns <  80 000 ns
(search-for-primes 100000 100044)       ; 100003, 100019, 100043    : ~ 100 000 ns = 100 000 ns
(search-for-primes 1000000 1000038)     ; 1000003, 1000033, 1000037 : ~  90 000 ns < 150 000 ns
