(ns replexpl.handler.example
  (:require [compojure.core :refer :all]
            [clojure.java.io :as io]
            [integrant.core :as ig]

            [replexpl.handler.welcome]
            [replexpl.handler.results]
            [hiccup.core :as h]
            [hiccup.page :as page]))

(defn tpl
  [content]
  (h/html
    (page/html5
      [:head
       [:link {:rel "stylesheet"
               :href "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
               :integrity "sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
               :crossorigin "anonymous"}]])
    (into [:body]
      content)))

(defmethod ig/init-key :replexpl.handler/example [_ options]
  (routes
    (context "/example" []
      (GET "/" []
        (io/resource "replexpl/handler/example/example.html")))

    (GET "/home" req
      (tpl (replexpl.handler.welcome/handle req)))
    (GET "/search-results" req
      (tpl (replexpl.handler.results/handle req)))))
