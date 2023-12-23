; Exercise 2.32: We can represent a set as a list of distinct elements, and we
; can represent the set of all subsets of the set as a list of lists. For
; example, if the set is (1 2 3), then the set of all subsets is
; (() (3) (2) (2 3) (1) (1 3) (1 2) (1 2 3)). Complete the following definition
; of a procedure that generates the set of subsets of a set and give a clear
; explanation of why it works:
;
; (defn subsets [s]
;   (if (empty? s)
;     (list '())
;     (let [rest (subsets (cdr s))]
;       (concat rest (map <??> rest)))))

(ns sicp.solutions.chapter-2)

(def cdr rest)

(defn subsets [s]
  (if (empty? s)
    (list '())
    (let [rest (subsets (cdr s))]
      (concat rest (map #(cons (first s) %) rest)))))

; Begin with a collection that initially comprises only the empty set ('()).
; Expand the collection by first incorporating all sets previously present in
; the collection and subsequently introducing sets formed by adding a distinct
; element to each of the existing sets in the collection.

(println (subsets '(1 2 3)))            ; (() (3) (2) (2 3) (1) (1 3) (1 2) (1 2 3))
