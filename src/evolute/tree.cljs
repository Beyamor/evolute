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

(defn random
  []
  (apply node/series
         (for [_ (range 10)]
           (random-terminal))))
