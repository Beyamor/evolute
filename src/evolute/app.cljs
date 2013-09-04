(ns evolute.app
  (:require [evolute.music :as music]
            [evolute.tree :as tree]
            [evolute.tree.node :as node]))

(def song-tree
  (node/series
    (node/repeat 2
                 (node/note :quarter :d3))
    (node/rest :quarter)
    (node/note :quarter :d3)))

(set! (.-onload js/window)
      #(let [song (tree/realize song-tree)]
         (set! (.-innerHTML (.-body js/document)) song)
         (music/play-song song :volume 50)))
