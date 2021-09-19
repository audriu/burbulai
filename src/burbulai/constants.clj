(ns burbulai.constants)

(def world-width 700)
(def world-height 700)

(def initial-state {:particles
                           {:p0 {:x 50 :y 660 :dx 2 :dy 8 :color 40}
                            :p1 {:x 150 :y 60 :dx 2 :dy 1 :color 140}}
                    :links [[:p0 :p1 70]]})
