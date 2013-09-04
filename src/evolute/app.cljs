(ns evolute.app
  (:require [evolute.music :as music]
            [evolute.song :as song]
            [evolute.gui :as gui]))

(defn song-model
  [index song-data]
  {:title (str "Song " (inc index))
   :index index
   :data song-data
   :selected false})

(defn create-initial-songs-model
  []
  (->>
    song/random
    (repeatedly 8)
    (map-indexed song-model)
    vec
    atom))

(set! (.-onload js/window)
      #(let [songs-model (create-initial-songs-model)]
         (gui/show-songs songs-model)))
