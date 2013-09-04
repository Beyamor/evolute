(ns evolute.tree.create
  (:require [evolute.tree.node :as node]
            [evolute.music.elements :as music-els]))

(defn random
  []
  (apply node/series
         (for [_ (range 5)]
             (node/note (rand-nth music-els/rhythms) (rand-nth music-els/notes)))))
