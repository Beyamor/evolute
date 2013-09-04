(ns evolute.app
  (:require [evolute.music :as music]
            [evolute.song :as song]
            [evolute.gui :as gui]))

(defn create-initial-songs-model
  []
  (atom (song/random-bunch 8)))

(set! (.-onload js/window)
      #(let [songs-model (create-initial-songs-model)]
         (gui/create songs-model)))
