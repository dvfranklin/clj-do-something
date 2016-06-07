(ns clj-do-something.view.books
  (require [clj-do-something.view.template :as template]
           [hiccup.core :refer [h]]
           [ring.util.anti-forgery :as anti-forgery]))

(defn book-form []
  [:div
   [:form {:action "/" :method "POST"}
    (anti-forgery/anti-forgery-field)
    [:label {:for "book"} "Enter book title:"]
    [:textarea {:name "book"}]
    [:button {:type "submit"} "Add book"]]])

(defn show-books [books]
  [:div
   [:ol
   (map
     (fn [book] [:li  (h (:title book))])
     books)]])

(defn index [books]
  (template/template "BookTracker by Clojure"
                 (book-form)
                 [:div
                  (show-books books)]))