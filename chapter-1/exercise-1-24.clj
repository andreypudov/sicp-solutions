; Exercise 1.24: Modify the timed-prime-test procedure of Exercise 1.22 to use
; fast-prime? (the Fermat method), and test each of the 12 primes you found in
; that exercise. Since the Fermat test has Θ(log n) growth, how would you expect
; the time to test primes near 1,000,000 to compare with the time needed to test
; primes near 1000? Do your data bear this out? Can you explain any discrepancy
; you find?

(ns sicp.solutions.chapter-1)

(def fast-prime-tests 100)

(defn runtime []
  (System/nanoTime))

(defn random [n]
  (int (rand n)))

(defn square [x]
    (* x x))

(defn smallest-divisor [n]
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

(defn expmod [base exp m]
  (cond (= exp 0) 1
        (even? exp) (rem (square (expmod base (/ exp 2) m)) m)
        :else (rem (* base (expmod base (- exp 1) m)) m)))

(defn fermat-test [n]
  (defn try-it [a]
    (= (expmod a n n) a))
  (try-it (+ 1 (random (- n 1)))))

(defn fast-prime? [n times]
  (cond (= times 0) true
        (fermat-test n) (fast-prime? n (- times 1))
        :else false))

(defn report-prime [elapsed-time]
  (print " *** ")
  (print elapsed-time))

(defn start-prime-test [n start-time]
  (if (fast-prime? n fast-prime-tests)
    (report-prime (- (runtime) start-time))))

(defn timed-prime-test [n]
  (newline)
  (print n)
  (start-prime-test n (runtime)))

(run! timed-prime-test '(1009    1013    1019      ; 600 000 ns >  25 000 ns
                         10007   10009   10037     ; 500 000 ns >  60 000 ns
                         100003  100019  100043    ; 400 000 ns > 100 000 ns
                         1000003 1000033 1000037)) ; 350 000 ns >  90 000 ns
