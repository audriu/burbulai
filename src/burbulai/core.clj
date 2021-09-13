(ns burbulai.core
  (:require [quil.core :as q]
            [quil.middleware :as m]))

(defn setup []
  (q/frame-rate 30)
  (q/fill 0)
  nil)

(defn update [state]
  state)

(defn draw [state]
  (q/text (pr-str state) 20 20))


(q/defsketch burbulai
             :title "burbulai"
             :size [500 500]
             :setup setup
             :update update
             :draw draw
             :features [:keep-on-top]
             :features [:exit-on-close]
             :middleware [m/fun-mode])
