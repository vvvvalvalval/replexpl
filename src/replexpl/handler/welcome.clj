(ns replexpl.handler.welcome
  (:require [hiccup.core :as h]
            [hiccup.page :as page]
            [ring.util.response :as ru]
            [ring.middleware.anti-forgery]))


(defn handle [req]
  [[:div.container
    [:div {:style "padding-top: 150px;"}
     [:div.row
      [:div.col-md-8.col-md-offset-2
       [:div
        [:div.text-center
         [:h1 "Dummy Facebook Search Engine"]
         [:p "A search engine on top of the Facebook Graph API"]]
        [:form.form.form-horizontal {:action "/search-results" :method "get"}
         [:input.form-control {:type "text" :name "search" :placeholder "Search Facebook!"}]]
        ]]]]
    ]])

(defn search [req]
  (ru/redirect "/search-results"))
