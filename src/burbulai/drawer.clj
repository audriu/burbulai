(ns burbulai.drawer
  (:require [quil.core :as q]))

(defn draw [state]
  (q/background 100)
  (doseq [[start-particle-id end-particle-id] (:links state)]
    (q/fill 255 255 255)
    (let [start-particle (start-particle-id (:particles state))
          end-particle (end-particle-id (:particles state))]
      (q/line (:x start-particle) (:y start-particle) (:x end-particle) (:y end-particle))))
  (doseq [{:keys [color x y]} (vals (:particles state))]
    (q/fill color 255 255)
    (q/ellipse x y 8 8)))
