(ns evolute.gui
  (:require [evolute.music :as music]
            [dommy.core :as dommy])
  (:use-macros [dommy.macros :only [node sel1]]))

(defn play-button
  [song] 
  (doto (node [:button {:type "button"} "Play"])
    (dommy/listen! :click
                   #(music/play-song song
                                     :volume 100
                                     :time-signature [4 4]
                                     :tempo 180))))

(defn render-song
  [index song]
  (node
    [:div.song
     (str "Song " (inc index))
     (play-button song)]))

(defn show-songs
  [songs]
  (dommy/replace-contents!
    (sel1 :body)
    (node [:div#songs
           (map-indexed render-song songs)])))
