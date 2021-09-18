(ns burbulai.drawer
  (:require [quil.core :as q]))

(defn draw [state]
  (q/background 100)
  (doseq [{:keys [color x y]} (vals state)]
    (q/fill color 255 255)
    (q/ellipse x y 8 8)))
