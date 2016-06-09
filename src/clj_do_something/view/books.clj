(ns clj-do-something.view.books
  (require [clj-do-something.view.template :as template]
           [hiccup.core :as h]
           [ring.util.anti-forgery :as anti-forgery])
  (import [hiccup.form]))

(defn book-form []
  [:div
   [:form {:action "/" :method "POST"}
    (anti-forgery/anti-forgery-field)
    [:label {:for "title"} "Enter book title:"]
    [:input {:type "text" :name "title"}]
    [:br]
    [:label {:for "author"} "Enter book author:"]
    [:input {:type "text" :name "author"}]
    [:br]
    [:label {:for "genre"} "Enter genre:"]
    [:input {:type "text" :name "genre"}]
    [:br]
    [:button {:type "submit"} "Add book"]]])

(defn show-books [books]
  [:div
   [:ol
   (map
     (fn [book] [:li  (h/html (:title book) " by " (:author book) ", Genre: " (:genre book))])
     books)]])

(defn index [books]
  (template/template "BookTracker by Clojure"
                 (book-form)
                 [:div
                  (show-books books)]))