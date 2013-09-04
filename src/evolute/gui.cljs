(ns evolute.gui
  (:require [evolute.music :as music]
            [dommy.core :as dommy])
  (:use-macros [dommy.macros :only [node sel1]]))

(defn render-song
  [{:keys [title]}]
  (node
    [:div.song
     title
     [:button.play {:type "button"} "Play"]
     "Parent: " [:input.parent {:type "checkbox"}]]))

(defn hook-up-play-button
  [song-node song]
  (dommy/listen!
    (sel1 song-node ".play")
    :click
    #(music/play-song song
                      :volume 100
                      :time-signature [4 4]
                      :tempo 180)))

(defn hook-up-parent-button
  [song-node songs index]
  (dommy/listen!
    (sel1 song-node ".parent")
    :click
    #(do
       (if (.-checked (sel1 song-node ".parent"))
         (dommy/add-class! song-node "parent")
         (dommy/remove-class! song-node "parent"))
       (swap! songs update-in [index :selected] not))))

(defn create-song-node
  [songs {:keys [data index] :as song}]
  (doto (render-song song)
    (hook-up-play-button data)
    (hook-up-parent-button songs index)))

(defn show-songs
  [songs]
  (dommy/replace-contents!
    (sel1 :body)
    (node [:div#songs
           (map (partial create-song-node songs) @songs)])))
