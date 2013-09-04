(ns evolute.gui
  (:require [evolute.music :as music]
            [dommy.core :as dommy])
  (:use-macros [dommy.macros :only [node sel1]]))

(defn render-song
  [{:keys [title]}]
  (node
    [:div.song
     title
     [:button.play {:type "button"} "Play"]]))

(defn show-songs
  [song-nodes]
  (dommy/replace-contents!
    (sel1 :body)
    (node [:div#songs song-nodes])))
