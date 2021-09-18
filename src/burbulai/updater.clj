(ns burbulai.updater)

(defn move-bubble [b]
  (-> b
      (update :x + (:_x b))
      (update :y + (:_y b))))

(defn update [state]
  (-> state
      (update-vals move-bubble)))
