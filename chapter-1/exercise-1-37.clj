; Exercise 1.37:
;
;   a. An infinite continued fraction is an expression of the form
;
;                  N1
;      f  = ------------------
;                     N2
;           D1 + -------------
;                        N3
;                D2 + --------
;                     D3 + ...
;
;      As an example, one can show that the infinite continued fraction
;      expansion with the Ni and the Di all equal to 1 produces 1/φ, where φ is
;      the golden ratio (described in Section 1.2.2). One way to approximate an
;      infinite continued fraction is to truncate the expansion after a given
;      number of terms. Such a truncation — a so-called k-term finite continued
;      fraction—has the form
;
;           N1
;      -----------
;             N2
;      D1 + ------
;          .    Nk
;           . + --
;            .  Dk
;
;      Suppose that n and d are procedures of one argument (the term index i)
;      that return the Ni and Di of the terms of the continued fraction. Define
;      a procedure cont-frac such that evaluating (cont-frac n d k) computes the
;      value of the k-term finite continued fraction. Check your procedure by
;      approximating 1/φ using
;
;      (cont-frac (lambda (i) 1.0)
;                 (lambda (i) 1.0)
;                 k)
;
;      for successive values of k. How large must you make k in order to get an
;      approximation that is accurate to 4 decimal places?
;
;   b. If your cont-frac procedure generates a recursive process, write one that
;      generates an iterative process. If it generates an iterative process,
;      write one that generates a recursive process.

(ns sicp.solutions.chapter-1)

; iterative process
(defn cont-frac [n d k]
  (defn iter [i result]
    (println i result)
    (if (= 0 i)
      result
      (iter (dec i) (/ (n i) (+ result (d i))))))
  (iter (dec k) (/ (n k) (d k))))

; recursive process
(defn cont-frac [n d k]
  (defn rec [i]
    (if (= i k)
      (/ (n i) (d i))
      (/ (n i) (+ (d i) (rec (inc i))))))
  (rec 1))

(println (cont-frac (fn [n] 1.0)        ; 0.6180555555555556 vs 1.6180339887
                    (fn [d] 1.0)
                    11))                ; 11 steps
