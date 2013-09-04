(ns evolute.music.elements)

(def notes
  (for [letter ["a" "b" "c" "d" "e" "f" "g"]
        number [5]]
    (str letter number)))

(def short-rhythms
  ["eighth"
   "quarter"])

(def rhythms
  (concat short-rhythms
          ["whole"
           "half"
           "dottedHalf"
           "dottedQuarter"
           "dottedEighth"]))
