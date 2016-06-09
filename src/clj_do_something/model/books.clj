(ns clj-do-something.model.books
  (:require [clojure.java.jdbc :as sql])
  (:import java.lang.Integer))

(def url "postgresql://localhost:5432/books")

(defn get-all []
  (into [] (sql/query url "select * from books order by id desc")))

(defn add-book [book]
  (sql/insert! url :books book))

(defn delete-book [id]
  (sql/delete! url :books ["id = ?" (java.lang.Integer/parseInt id)]))
