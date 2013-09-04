(ns evolute.song
  (:require [evolute.music.elements :as music-els])
  (:require-macros [evolute.util.macros :as util-macros]))

(defn- random-note
  []
  [:note (rand-nth music-els/rhythms) (rand-nth music-els/notes)])

(defn- random-rest
  []
  [:rest (rand-nth music-els/short-rhythms)])

(defn- random-element
  []
  (util-macros/probabilities
    5 (random-rest)
    (random-note)))

(defn random-data
  []
  (->> random-element repeatedly (take (+ 10 (rand-int 5)))))

(defn song-model
  [index song-data]
  {:index index
   :data song-data
   :selected false})

(defn random-bunch
  [number-to-make]
  (->>
    random-data
    (repeatedly number-to-make)
    (map-indexed song-model)
    vec))

(defn choose-parents
  [parents]
  (let [rand-parent-index #(rand-int (count parents))
        mother-index (rand-parent-index)
        father-index (->> rand-parent-index repeatedly (remove #{mother-index}) first)]
    [(nth parents mother-index) (nth parents father-index)]))

(defn flip-coin
  []
  (< (rand) 0.5))

(defn add-to-either
  [el child1 child2]
  (if (flip-coin)
    [(concat child1 [el]) child2]
    [child1 (concat child2 [el])]))

(defn remove-from-either
  [mother father]
  (if (flip-coin)
    [(first mother) (rest mother) father]
    [(first father) mother (rest father)]))

(defn breed
  [mother father]
  (loop [mother mother, father father, child1 [], child2 []]
    (cond
      (and (empty? mother) (empty? father))
      [child1 child2]

      (empty? mother)
      (let [[el & rest-of-father] father
            [child1 child2] (add-to-either el child1 child2)]
        (recur mother rest-of-father child1 child2))

      (empty? father)
      (let [[el & rest-of-mother] mother
            [child1 child2] (add-to-either el child1 child2)]
        (recur rest-of-mother father child1 child2))

      :else
      (let [[el mother father] (remove-from-either mother father)
            [child1 child2] (add-to-either el child1 child2)]
        (recur mother father child1 child2)))))

(defn breed-set
  [number-to-breed parent-candidates]
  (->>
    (let [number-of-pairs (/ number-to-breed 2)]
      (for [i (range number-of-pairs)
            :let [[mother father] (choose-parents parent-candidates)]
            child-data (breed (:data mother) (:data father))]
        child-data))
    (map-indexed song-model)
    vec))
