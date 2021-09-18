(ns burbulai.core
  (:require [burbulai.drawer :refer [draw]]
            [burbulai.initial-state :refer [initial-state]]
            [burbulai.updater :refer [update]]
            [quil.core :as q]
            [quil.middleware :as m]))

(defn setup []
  (q/frame-rate 30)
  (q/color-mode :hsb)
  initial-state)

(defn -main [& _args]
  (q/defsketch burbulai
               :title "burbulai"
               :size [500 500]
               :setup setup
               :update update
               :draw draw
               :features [:keep-on-top]
               :features [:exit-on-close]
               :middleware [m/fun-mode]))
