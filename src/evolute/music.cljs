(ns evolute.music)

(defprotocol Element
  (add-to-instrument [el instrument]))

(deftype Note [rhythm pitch]
  Element
  (add-to-instrument [this instrument]
    (.note instrument rhythm pitch)))

(deftype Rest [rhythm]
  Element
  (add-to-instrument [this instrument]
    (.rest instrument rhythm)))

(defmulti parse-element first)
(defmethod parse-element :note
  [[_ rhythm pitch]]
  (Note. rhythm pitch))
(defmethod parse-element :rest
  [[_ rhythm]]
  (Rest. rhythm))

(defn add-elements
  [instrument song]
  (doseq [el song] 
    (add-to-instrument el instrument)))

(defn play-song
  [song & {:keys [volume]
           :or {volume 100}}]
  (let [music (js/BandJS.)
        instrument (.createInstrument music "square" "oscillators")]
    (doto instrument
      (add-elements
        (map parse-element song))
      .finish)
    (doto music
      (.setTimeSignature 2 2)
      (.setTempo 180)
      (.setMasterVolume (/ volume 100))
      .end
      .play)))
