; Exercise 2.29: A binary mobile consists of two branches, a left branch and a
; right branch. Each branch is a rod of a certain length, from which hangs
; either a weight or another binary mobile. We can represent a binary mobile
; using compound data by constructing it from two branches (for example, using
; list):
;
; (defn make-mobile [left right]
;   (list left right))
;
; A branch is constructed from a length (which must be a number) together with a
; structure, which may be either a number (representing a simple weight) or
; another mobile:
;
; (defn make-branch [length structure]
;   (list length structure))
;
; a. Write the corresponding selectors left-branch and right-branch, which
;    return the branches of a mobile, and branch-length and branch-structure,
;    which return the components of a branch.
; b. Using your selectors, define a procedure total-weight that returns the
;    total weight of a mobile.
; c. A mobile is said to be balanced if the torque applied by its top-left
;    branch is equal to that applied by its top-right branch (that is, if the
;    length of the left rod multiplied by the weight hanging from that rod is
;    equal to the corresponding product for the right side) and if each of the
;    submobiles hanging off its branches is balanced. Design a predicate that
;    tests whether a binary mobile is balanced.
; d. Suppose we change the representation of mobiles so that the constructors
;    are
;
;    (define (make-mobile left right) (cons left right))
;    (define (make-branch length structure)
;      (cons length structure))
;
;    How much do you need to change your programs to convert to the new
;    representation?

(ns sicp.solutions.chapter-2)

(defn make-mobile [left right]
  (list left right))

(defn make-branch [length structure]
  (list length structure))

(defn left-branch [mobile]
  (first mobile))

(defn right-branch [mobile]
  (second mobile))

(defn branch-length [branch]
  (first branch))

(defn branch-structure [branch]
  (second branch))

(declare total-weight)

(defn branch-weight [branch]
  (if (coll? branch)
    (let [structure (branch-structure branch)]
      (if (number? structure) structure
          (total-weight structure)))
      branch))

(defn total-weight [mobile]
  (if (coll? mobile)
    (+ (branch-weight (left-branch mobile))
       (branch-weight (right-branch mobile)))
    mobile))

(defn torque [branch]
  (if (coll? branch)
    (* (branch-length branch)
       (total-weight (branch-structure branch)))
    branch))

(defn balanced? [mobile]
  (if (coll? mobile)
    (and (= (torque (left-branch mobile))
            (torque (right-branch mobile)))
         (if (coll? (branch-structure (left-branch mobile)))
           (balanced? (branch-structure (left-branch mobile)))
           true)
         (if (coll? (branch-structure (right-branch mobile)))
           (balanced? (branch-structure (right-branch mobile)))
           true))
    mobile))

(def mobile (make-mobile 3 6))
(def branch (make-branch 8 4))

(println mobile)                        ; (3 6)
(println branch)                        ; (8 4)

(println (left-branch mobile))          ; 3
(println (right-branch mobile))         ; 6
(println (branch-length branch))        ; 8
(println (branch-structure branch))     ; 4

(println (branch-weight branch))        ; 4
(println (total-weight mobile))         ; 9

(println (torque branch))               ; 32
(println (balanced? mobile))            ; false

(def mobile1 (make-mobile
              (make-branch 2 3)
              (make-branch 2 3)))
(def mobile2 (make-mobile
              (make-branch 10 mobile1)
              (make-branch 12 5)))
(println (balanced? mobile2))           ; true


(defn make-mobile [left right]
  (cons left right))
(defn make-branch [length structure]
  (cons length structure))

; No changes are required in order to use updated implementations of make-mobile
; and make-branch functions.

(println mobile)                        ; (3 6)
(println branch)                        ; (8 4)

(println (left-branch mobile))          ; 3
(println (right-branch mobile))         ; 6
(println (branch-length branch))        ; 8
(println (branch-structure branch))     ; 4

(println (branch-weight branch))        ; 4
(println (total-weight mobile))         ; 9

(println (torque branch))               ; 32
(println (balanced? mobile))            ; false
