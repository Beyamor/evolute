(ns evolute.tree)

(defmulti realize :type)

(defmethod realize :note
  [{:keys [rhythm pitch]}]
  [[:note
    (name rhythm)
    (.toUpperCase (name pitch))]])

(defmethod realize :rest
  [{:keys [rhythm]}]
  [[:rest
    (name rhythm)]])

(defmethod realize :series
  [{:keys [children]}]
  (->> children
    (map realize)
    (apply concat)))

(defmethod realize :repeat
  [{:keys [number series]}]
  (->> (realize series)
    (repeat number)
    (apply concat)))
