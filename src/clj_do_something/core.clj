(ns clj-do-something.core
  (:require [compojure.core :refer [defroutes]]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.adapter.jetty :as j]
            [hiccup.core :as h]
            [clj-do-something.controller.books :as books]
            [clj-do-something.model.migrate :as schema]))

(defonce server (atom nil))


(defroutes routes
           books/routes
           (route/resources "/"))


(def app (wrap-defaults routes site-defaults))



(defn -main []
  (when @server
    (.stop @server))
  (schema/migrate)
  (reset! server (j/run-jetty app {:port 3000 :join? false})))