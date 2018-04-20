(ns replexpl.handler.results
  (:require [clojure.repl :refer :all]
            [clojure.pprint :refer [pprint]]
            [replexpl.env :as env]
            [org.httpkit.client :as http]
            [cheshire.core :as json]))

(defn api-search
  [q]
  (->
    @(http/request
       {:method :get
        :url "https://graph.facebook.com/v2.12/search"
        :query-params
        {"q" q
         "fields" "id, name, fan_count"
         "type" "place"
         "access_token" env/fb-token}})
    :body
    (json/decode true)
    :data))

(defn extract-page
  [search-result]
  {:page-id (:id search-result)
   :page-name (:name search-result)
   :n-likes (:fan_count search-result)})

(defn api-posts
  [page-id]
  (->
    @(http/request
       {:method :get
        :url (str "https://graph.facebook.com/v2.8/" page-id "/posts")
        :query-params
        {"access_token" env/fb-token}})
    :body
    (json/decode true)))

(defn extract-last-post
  [posts-resp]
  (-> posts-resp
    :data
    first
    :message))

(def a 42)

(defn search
  [q]
  {:search q
   :search-results
   (->> (api-search q)
     (take 5)
     (map extract-page)
     (pmap (fn [page]
             (assoc page
               :last-post
               (extract-last-post (api-posts (:page-id page))))))
     vec)})

(def mock-data
  {:search "zztop"
   :search-results
   (repeat 3
     {:page-name "ZZ Top"
      :n-likes 3242
      :last-post "Back to La Grange!"})})

(defn handle [req]
  ;; BUG IN HERE
  (let [q (:query-string req)
        results-data (search q)]
    [[:div.container
      [:div.row
       [:div.col-md-6.col-md-offset-3
        [:div
         [:h1 "Search results"]
         [:div
          [:p
           (count (:search-results results-data)) " search results for "
           [:b q] ":"]
          [:div
           (for [sr (:search-results results-data)]
             [:div.panel.panel-default
              [:div.panel-heading
               [:span.h3 (:page-name sr)]
               [:span.pull-right
                [:span.glyphicon.glyphicon-thumbs-up {:aria-hidden "true"}]
                " "
                (:n-likes sr)]]
              [:div.panel-body
               (when (:last-post sr)
                 [:blockquote.blockquote (:last-post sr)])]])]]]]]
      ]]))
