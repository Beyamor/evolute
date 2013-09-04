(ns evolute.util.macros)

(defn- probabilities-helper
  [accumulated-probability n clauses]
  (when (seq clauses)
    (let [[clause & remaining-clauses] clauses]
      (if (= 2 (count clause))
        ; probability and result
        (let [[prob result] clause
              accumulated-probability (+ prob accumulated-probability)]
          `(if (< ~n ~accumulated-probability)
             ~result
             ~(probabilities-helper accumulated-probability n remaining-clauses)))
        ; default
        (first clause)))))

(defmacro probabilities
  [& clauses]
  (let [probs-and-results (partition-all 2 clauses)
        n (gensym)]
    `(let [~n (* 100 (rand))]
       ~(probabilities-helper 0 n probs-and-results))))
