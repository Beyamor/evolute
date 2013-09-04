(ns evolute.tree.node
  (:refer-clojure :exclude [rest repeat]))

(defn node
  [type data]
  (into {:type type} data))

(defn series
  [& children]
  (node :series
        {:children children}))

(defn note
  [rhythm pitch]
  (node :note
        {:rhythm rhythm
         :pitch pitch}))

(defn rest
  [rhythm]
  (node :rest
        {:rhythm rhythm}))

(defn repeat
  [number & children]
  (node :repeat
        {:number number
         :series (apply series children)}))
