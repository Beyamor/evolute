(ns evolute.app
  (:require [evolute.music :as music]
            [evolute.song :as song]
            [evolute.gui :as gui]))

(set! (.-onload js/window)
      #(->>
         song/random
         (repeatedly 8)
         gui/show-songs))
