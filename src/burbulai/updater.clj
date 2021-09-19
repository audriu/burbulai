(ns burbulai.updater
  (:require [burbulai.constants :refer [world-height world-width]]))

(defn move-particle [{:keys [x y dx dy] :as b}]
  (assoc b :x (+ x dx) :y (+ y dy)))

(defn bounce-walls [{:keys [x y dx dy] :as b}]
  (let [new-x (if (> x world-width) world-width x)
        new-y (if (> y world-height) world-height y)
        new-dx (if (or (< x 0) (> x world-width)) (- dx) dx)
        new-dy (if (or (< y 0) (> y world-height)) (- dy) dy)]
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
      (update :particles #(update-vals % bounce-walls))
      enforce-links))
