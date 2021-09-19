(ns burbulai.updater
  (:require [burbulai.constants :refer [world-height world-width]]))

(defn move-bubble [{:keys [x y dx dy] :as b}]
  (assoc b :x (+ x dx) :y (+ y dy)))

(defn bounce-walls [{:keys [x y dx dy] :as b}]
  (let [new-x (if (> x world-width) world-width x)
        new-y (if (> y world-height) world-height y)
        new-dx (if (or (< x 0) (> x world-width)) (- dx) dx)
        new-dy (if (or (< y 0) (> y world-height)) (- dy) dy)]
    (assoc b :x new-x :y new-y :dx new-dx :dy new-dy)))

(defn update-state [state]
  (-> state
      (update :particles #(update-vals % move-bubble))
      (update :particles #(update-vals % bounce-walls))))
