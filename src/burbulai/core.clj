(ns burbulai.core
  (:require [burbulai.constants :refer [initial-state world-height world-width]]
            [burbulai.drawer :refer [draw]]
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
               :size [world-width world-height]
               :setup setup
               :update update
               :draw draw
               :features [:keep-on-top]
               :features [:exit-on-close]
               :middleware [m/fun-mode]))
