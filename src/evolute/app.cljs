(ns evolute.app
  (:require [evolute.music :as music]
            [evolute.song :as song]))

(set! (.-onload js/window)
      #(let [song (song/random)]
         (set! (.-innerHTML (.-body js/document)) song)
         (music/play-song song
                          :volume 100
                          :time-signature [4 4]
                          :tempo 120)))
