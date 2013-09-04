(ns evolute.song)

(defmulti from-tree :type)

(defmethod from-tree :note
  [{:keys [rhythm pitch]}]
  [[:note
    (name rhythm)
    (.toUpperCase (name pitch))]])

(defmethod from-tree :rest
  [{:keys [rhythm]}]
  [[:rest
    (name rhythm)]])

(defmethod from-tree :series
  [{:keys [children]}]
  (->> children
    (map from-tree)
    (apply concat)))

(defmethod from-tree :repeat
  [{:keys [number series]}]
  (->> (from-tree series)
    (repeat number)
    (apply concat)))
