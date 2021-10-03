(ns burbulai.verlet
  (:require [burbulai.constants :refer [world-height world-width]]))

(defn move-particle [{:keys [x y dx dy] :as b}]
  (let [new-x (+ x dx)
        new-dx (if (or (< new-x 0) (> new-x world-width)) (- dx) dx)
        new-x (if (> new-x world-width) world-width new-x)
        new-y (+ y dy)
        new-dy (if (or (< new-y 0) (> new-y world-height)) (- dy) dy)
        new-y (if (> new-y world-height) world-height new-y)]
    (assoc b :x new-x :y new-y :dx new-dx :dy new-dy)))

(defn get-offsets [links particles]
  (for [[point1-id point2-id link-length] links]
    (let [point1 (point1-id particles)
          point2 (point2-id particles)
          dx (- (:x point1) (:x point2))
          dy (- (:y point1) (:y point2))
          distance (Math/sqrt (+ (* dx dx) (* dy dy)))
          difference (- distance link-length)
          percent (/ difference distance 2)
          offset-x (* dx percent)
          offset-y (* dy percent)]
      [point1-id point2-id offset-x offset-y])))

(defn enforce-links [{:keys [links particles] :as state}]
  (let [offsets (get-offsets links particles)]
    (reduce
      (fn [state [id-1 id-2 off-x off-y]]
        (-> state
            (update-in [:particles id-1 :x] - off-x)
            (update-in [:particles id-1 :y] - off-y)
            (update-in [:particles id-2 :x] + off-x)
            (update-in [:particles id-2 :y] + off-y)))
      state
      offsets)))

(defn update-state [state]
  (-> state
      (update :particles #(update-vals % move-particle))
      enforce-links))
