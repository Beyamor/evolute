(ns evolute.app
  (:require [evolute.music :as music]
            [evolute.song :as song]
            [evolute.tree :as tree]
            [evolute.tree.node :as node]))

(set! (.-onload js/window)
      #(let [song (song/from-tree (tree/random))]
         (set! (.-innerHTML (.-body js/document)) song)
         (music/play-song song
                          :volume 100
                          :time-signature [4 4]
                          :tempo 120)))
