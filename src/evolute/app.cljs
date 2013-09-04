(ns evolute.app
  (:require [evolute.music :as music]
            [evolute.song :as song]
            [evolute.gui :as gui]
            [dommy.core :as dommy])
  (:use-macros [dommy.macros :only [sel1]]))

(defn wrap-song-data
  [index song-data]
  {:title (str "Song " (inc index))
   :data song-data})

(defn hook-up-play-button
  [song-node song]
  (dommy/listen! (sel1 song-node ".play")
    :click
    #(music/play-song song
                      :volume 100
                      :time-signature [4 4]
                      :tempo 180)))

(defn create-song-node
  [{:keys [data] :as song}]
  (doto (gui/render-song song)
    (hook-up-play-button data)))

(set! (.-onload js/window)
      #(->>
         song/random
         (repeatedly 8)
         (map-indexed wrap-song-data)
         (map create-song-node)
         gui/show-songs))
