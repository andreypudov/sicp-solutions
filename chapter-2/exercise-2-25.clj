; Exercise 2.25: Give combinations of cars and cdrs that will pick 7 from each
; of the following lists:
;
; (1 3 (5 7) 9)
; ((7))
; (1 (2 (3 (4 (5 (6 7))))))

(ns sicp.solutions.chapter-2)

(def car first)
(def cdr rest)

(def l1 '(1 3 (5 7) 9))
(def l2 '((7)))
(def l3 '(1 (2 (3 (4 (5 (6 7)))))))

(println (cdr (car (cdr (cdr l1)))))    ; 7
(println (car l2))                      ; 7
(println (cdr
          (car
           (cdr
            (car
             (cdr
              (car
               (cdr
                (car
                 (cdr
                  (car
                   (cdr l3))))))))))))  ; 7
