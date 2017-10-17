(defproject replexpl "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [duct/core "0.5.1"]
                 [duct/module.logging "0.2.0"]
                 [duct/module.web "0.5.5"]

                 [hiccup "1.0.5"]
                 [http-kit "2.2.0"]
                 [cheshire "5.7.1"]
                 [midje "1.8.3"]
                 ]
  :plugins [[duct/lein-duct "0.9.2"]]
  :main ^:skip-aot replexpl.main
  :resource-paths ["resources" "target/resources"]
  :prep-tasks     ["javac" "compile" ["run" ":duct/compiler"]]
  :profiles
  {:dev  [:project/dev :profiles/dev]
   :repl {:prep-tasks   ^:replace ["javac" "compile"]
          :repl-options {:init-ns user
                         :nrepl-middleware
                         [sc.nrepl.middleware/wrap-letsc]}}
   :uberjar {:aot :all}
   :profiles/dev {}
   :project/dev  {:source-paths   ["dev/src"]
                  :resource-paths ["dev/resources"]
                  :dependencies   [[integrant/repl "0.2.0"]
                                   [eftest "0.3.1"]
                                   [kerodon "0.8.0"]
                                   [vvvvalvalval/scope-capture "0.1.1"]
                                   [vvvvalvalval/scope-capture-nrepl "0.2.0"]]}})
