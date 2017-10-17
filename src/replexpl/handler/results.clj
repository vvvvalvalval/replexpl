(ns replexpl.handler.results
  (:require [clojure.repl :refer :all]
            [clojure.pprint :refer [pprint]]
            [replexpl.env :as env]
            [org.httpkit.client :as http]
            [cheshire.core :as json]))

(def mock-data
  {:search "zztop"
   :search-results
   (repeat 3
     {:page-name "ZZ Top"
      :n-likes 3242
      :last-post "Back to La Grange!"})})

(defn handle [req]
  (let [;; FIXME
        results-data mock-data
        search-string (:query-string req)]
    [[:div.container
      [:div.row
       [:div.col-md-6.col-md-offset-3
        [:div
         [:h1 "Search results"]
         [:div
          [:p
           (count (:search-results results-data)) " search results for "
           [:b search-string] ":"]
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
