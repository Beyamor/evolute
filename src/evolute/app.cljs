(ns evolute.app
  (:require [evolute.music :as music]
            [evolute.tree :as tree]
            [evolute.tree.node :as node]))

(set! (.-onload js/window)
      #(->
         (node/series
           (node/repeat 2
                        (node/note :quarter :d3))
           (node/rest :quarter)
           (node/note :quarter :d3))
         tree/realize
         (music/play-song :volume 50)))
