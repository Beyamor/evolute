(ns evolute.music)

(defmulti add-to-instrument
  (fn [el _] (first el)))

(defmethod add-to-instrument :note
  [[_ rhythm pitch] instrument]
  (.note instrument rhythm pitch))

(defmethod add-to-instrument :rest
  [[_ rhythm] instrument]
  (.rest instrument rhythm))

(defn add-elements
  [instrument song]
  (doall
    (map #(add-to-instrument % instrument) song)))

(defn play-song
  [song & {:keys [volume]
           :or {volume 100}}]
  (let [music (js/BandJS.)
        instrument (.createInstrument music "square" "oscillators")]
    (doto instrument
      (add-elements song)
      .finish)
    (doto music
      (.setTimeSignature 2 2)
      (.setTempo 180)
      (.setMasterVolume (/ volume 100))
      .end
      .play)))
