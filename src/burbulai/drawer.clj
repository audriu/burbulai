(ns burbulai.drawer
  (:require [quil.core :as q]))

(defn draw [state]
  (doseq [{:keys [color x y]} (vals state)]
    (q/fill color 255 255)
    (q/ellipse x y 8 8)))
