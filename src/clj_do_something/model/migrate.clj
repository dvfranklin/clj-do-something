(ns clj-do-something.model.migrate
  (:require [clojure.java.jdbc :as sql]
            [clj-do-something.model.books :as books]))

(defn migrated? []
  (-> (sql/query books/url
                 [(str "select count(*) from information_schema.tables "
                       "where table_name='books'")])
      first :count pos?))

(defn migrate []
  (when (not (migrated?))
    (flush)
    (sql/db-do-commands books/url
                        (sql/create-table-ddl
                          :books
                          [[:id :serial "PRIMARY KEY"]
                           [:title :varchar "NOT NULL"]]))))
