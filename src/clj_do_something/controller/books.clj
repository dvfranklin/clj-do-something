(ns clj-do-something.controller.books
  (require  [compojure.core :refer [defroutes GET POST]]
            [clojure.string :as str]
            [ring.util.response :as ring]
            [clj-do-something.model.books :as model]
            [clj-do-something.view.books :as view]))

(defn index []
  (view/index (model/get-all)))

(defn create [book]
  (model/add-book book)
  (ring/redirect "/"))

(defroutes routes
           (GET "/" [] (index))
           (POST "/" [book] (create book)))


