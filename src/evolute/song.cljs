(ns evolute.song
  (:require [evolute.music.elements :as music-els])
  (:require-macros [evolute.util.macros :as util-macros]))

(defn- random-note
  []
  [:note (rand-nth music-els/rhythms) (rand-nth music-els/notes)])

(defn- random-rest
  []
  [:rest (rand-nth music-els/short-rhythms)])

(defn- random-element
  []
  (util-macros/probabilities
    5 (random-rest)
    (random-note)))

(defn random
  []
  (->> random-element repeatedly (take (+ 10 (rand-int 5)))))
