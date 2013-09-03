(ns evolute.app
  (:require [evolute.music :as music]))

(set! (.-onload js/window)
      #(music/play-song
         [[:note "quarter" "D3"]
          [:note "quarter" "D3"]
          [:rest "quarter"]
          [:note "quarter" "D3"]]
         :volume 50))
