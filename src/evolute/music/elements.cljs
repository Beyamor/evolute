(ns evolute.music.elements)

(def notes
  (for [letter ["a" "b" "c" "d" "e" "f" "g"]
        number [3 4 5 6]]
    (str letter number)))

(def rhythms
  ["whole"
   "half"
   "quarter"])
