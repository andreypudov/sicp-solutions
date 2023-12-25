; Exercise 2.37: Suppose we represent vectors v = (vi ) as sequences of numbers,
; and matrices m = (mij ) as sequences of vectors (the rows of the matrix). For
; example, the matrix
;
; | 1 2 3 4 |
; | 4 5 6 6 |
; | 6 7 8 9 |
;
; is represented as the sequence ((1 2 3 4) (4 5 6 6) (6 7 8 9)). With this
; representation, we can use sequence operations to concisely express the basic
; matrix and vector operations. These operations (which are described in any
; book on matrix algebra) are the following:
;
;     (dot-product v w) returns the sum Σ_{i}v_{i}w_{i};
; (matrix-*-vector m v) returns the vector t, where t_{i} = Σ_{j}m_{ij}v_{j};
; (matrix-*-matrix m n) returns the matrix p, where p_{ij} = Σ_{k}m_{ik}n_{kj};
;         (transpose m) returns the matrix n, where n_{ij} = m_{ji}.
;
; We can define the dot product as
;
; (defn dot-product [v w]
;   (accumulate + 0 (map * v w)))
;
; Fill in the missing expressions in the following procedures for computing the
; other matrix operations. (The procedure accumulate-n is defined in Exercise 2.36.)
;
; (defn matrix-*-vector [m v]
;   (map ⟨??⟩ m))
; (defn transpose [mat]
;   (accumulate-n ⟨??⟩ ⟨??⟩ mat))
; (defn matrix-*-matrix [m n]
;   (let [cols (transpose n)]
;     (map ⟨??⟩ m)))

(ns sicp.solutions.chapter-2)

(defn accumulate [op initial sequence]
  (if (empty? sequence)
    initial
    (op (first sequence)
        (accumulate op initial (rest sequence)))))

(defn accumulate-n [op init seqs]
  (if (empty? (first seqs))
    '()
    (cons (accumulate op init (map first seqs))
          (accumulate-n op init (map rest seqs)))))

(defn dot-product [v w]
  (accumulate + 0 (map * v w)))
(defn matrix-*-vector [m v]
  (map #(dot-product v %) m))
(defn transpose [mat]
  (accumulate-n cons '() mat))
(defn matrix-*-matrix [m n]
  (let [cols (transpose n)]
    (map #(matrix-*-vector cols %) m)))

(def v '(1 2 3))
(def w '(2 3 4))
(def m '((1 2 3) (4 5 6)))
(def n '((1 4) (2 5) (3 6)))

(println (dot-product v w))             ; 20
(println (matrix-*-vector m v))         ; (14 32)
(println (transpose m))                 ; ((1 4) (2 5) (3 6))
(println (matrix-*-matrix m n))         ; ((14 32) (32 77))
