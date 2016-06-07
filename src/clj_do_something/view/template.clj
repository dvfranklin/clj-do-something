(ns clj-do-something.view.template
  (:require [hiccup.page :as h]))

(defn template [title & body]
  (h/html5
    [:head
     [:title title]]
    [:body
     [:div {:id "header"}
      [:h1 {:class "container"} "BookTracker by Clojure"]]
     [:div {:id "content" :class "container"} body]]))

