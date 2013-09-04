(ns evolute.tree
  (:require [evolute.tree.node :as node]
            [evolute.music.elements :as music-els])
  (:require-macros [evolute.util.macros :as util-macros]))

(defn- random-note
  []
  (node/note
    (rand-nth music-els/rhythms) (rand-nth music-els/notes)))

(defn- random-rest
  []
  (node/rest
    (rand-nth music-els/short-rhythms)))

(defn- random-terminal
  []
  (util-macros/probabilities
   5 (random-rest)
    (random-note)))

(defn rand-int-range
  [min max]
  (+ min (rand-int (- max min))))

(defn- random-branch
  [depth]
  (if (zero? depth)
    (random-terminal)
    (util-macros/probabilities
      5 (random-terminal)
      10 (apply node/repeat
                (repeatedly
                  (rand-int-range 2 4)
                  #(random-branch (dec depth))))
      (apply node/series
             (repeatedly
               (rand-int-range 1 5)
               #(random-branch (dec depth)))))))

(defn random
  []
  (apply node/series
         (for [_ (range (rand-int-range 3 5))]
           (random-branch (rand-int-range 2 3)))))
