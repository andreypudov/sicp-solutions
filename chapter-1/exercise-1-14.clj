; Exercise 1.14: Draw the tree illustrating the process generated by the
; count-change procedure of Section 1.2.2 in making change for 11 cents. What
; are the orders of growth of the space and number of steps used by this process
; as the amount to be changed increases?

(ns sicp.solutions.chapter-1)

(defn first-denomination [kinds-of-coins]
  (cond (= kinds-of-coins 1) 1
        (= kinds-of-coins 2) 5
        (= kinds-of-coins 3) 10
        (= kinds-of-coins 4) 25
        (= kinds-of-coins 5) 50))

(defn cc [amount kinds-of-coins]
  (do
  (println amount " / " kinds-of-coins)
  (cond (= amount 0) 1
        (or (< amount 0) (= kinds-of-coins 0)) 0
        :else (+ (cc amount
                     (- kinds-of-coins 1))
                 (cc (- amount
                        (first-denomination
                         kinds-of-coins))
                     kinds-of-coins)))))

(defn count-change [amount]
  (cc amount 5))

(println (count-change 11))             ; 4

;                                    11 / 5
;                                   /     \
;                             11 / 4       -39 / 5
;                            /      \
;                      11 / 3       -14 / 4
;                     /      \
;               11 / 2         ----------------------------1 / 3
;              /      \                                   /     \
;        11 / 1        6 / 2                         1 / 2       -9 / 3
;       /      \                                    /     \
; 11 / 0        10 / 1                         1 / 1       -4 / 4
;              /      \                       /     \
;        10 / 0        9 / 1             1 / 0       0 / 1
;                     /     \
;                9 / 0       8 / 1
;                           /     \
;                      8 / 0       7 / 1
;                                 /     \
;                            7 / 0       6 / 1
;                                       /     \
;                                  6 / 0       5 / 1
;                                             /     \
;                                        5 / 0       4 / 1
;                                                   /     \
;                                              4 / 0       3 / 1
;                                                         /     \
;                                                    3 / 0       2 / 1
;                                                               /     \
;                                                          2 / 0       1 / 1
;                                                                     /     \
;                                                                1 / 0       0 / 1
;
; The order of growth in space: Θ(n)
; Number of steps: T(n, 5) = Θ(n^5)
