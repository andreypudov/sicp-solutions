; Exercise 1.22: Most Lisp implementations include a primitive called runtime
; that returns an integer that specifies the amount of time the system has been
; running (measured, for example, in microseconds). Thee following
; timedprime-test procedure, when called with an integer n, prints n and checks
; to see if n is prime. If n is prime, the procedure prints three asterisks
; followed by the amount of time used in performing the test.
;
; (define (timed-prime-test n)
;   (newline)
;   (display n)
;   (start-prime-test n (runtime)))
; (define (start-prime-test n start-time)
;   (if (prime? n)
;       (report-prime (- (runtime) start-time))))
; (define (report-prime elapsed-time)
;   (display " *** ")
;   (display elapsed-time))
;
; Using this procedure, write a procedure search-for-primes that checks the
; primality of consecutive odd integers in a specified range. Use your procedure
; to find the three smallest primes larger than 1000; larger than 10,000; larger
; than 100,000; larger than 1,000,000. Note the time needed to test each prime.
; Since the testing algorithm has order of growth of Θ(⎷n), you should expect
; that testing for primes around 10,000 should take about ⎷10 times as long as
; testing for primes around 1000. Do your timing data bear this out? How well do
; the data for 100,000 and 1,000,000 support the Θ(⎷n) prediction? Is your
; result compatible with the notion that programs on your machine run in time
; proportional to the number of steps required for the computation?

(ns sicp.solutions.chapter-1)

(defn runtime []
  (System/nanoTime))

(defn smallest-divisor [n]
  (defn square [x]
    (* x x))
  (defn divides? [a b]
    (= (rem b a) 0))
  (defn find-divisor [n test-divisor]
    (cond (> (square test-divisor) n) n
          (divides? test-divisor n) test-divisor
          :else (find-divisor n (+ test-divisor 1))))
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

(search-for-primes 1000 1020)           ; 1009, 1013, 1019          : ~  40 000 ns
(search-for-primes 10000 10038)         ; 10007, 10009, 10037       : ~  80 000 ns : ratio 2    < ⎷10
(search-for-primes 100000 100044)       ; 100003, 100019, 100043    : ~ 100 000 ns : ratio 1.25 < ⎷10
(search-for-primes 1000000 1000038)     ; 1000003, 1000033, 1000037 : ~ 150 000 ns : ratio 1.5  < ⎷10
