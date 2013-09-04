(ns evolute.gui
  (:require [evolute.music :as music]
            [dommy.core :as dommy]
            [evolute.song :as song])
  (:use-macros [dommy.macros :only [node sel1]]))

(defn render-song
  [{:keys [index]}]
  (node
    [:div.song
     (str "Song " index)
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

(declare create)

(defn next-generation-button
  [songs]
  (doto (node [:button {:type "button"} "Next generation"])
    (dommy/listen!
      :click
      (fn []
        (when (>= (count (filter :selected @songs)) 2)
          (swap! songs
                 #(song/breed-set (count %) (filter :selected %)))
          (create songs))))))

(defn create
  [songs]
  (dommy/replace-contents!
    (sel1 :body)
    (node [:div#songs
           (map (partial create-song-node songs) @songs)
           (next-generation-button songs)])))
