(defproject bond "1.0.0"
  :description "A Functional Programming Environment for the Automatic Creation of Software Release Candidates"
  :url "https://linneman.github.io/bond"
  :license {:name "GNU Lesser General Public License"
            :url "http://www.gnu.org/licenses/gpl.html"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [org.clojure/tools.cli "0.3.5"]
                 [me.raynes/conch "0.8.0"]
                 [clj-glob "1.0.0"]
                 [org.clojure/tools.nrepl "0.2.13"]
                 [org.apache.commons/commons-email "1.5"]
                 [ring/ring-core "1.8.0"]
                 [ring/ring-jetty-adapter "1.8.0"]
                 [ring-json-params "0.1.3"]
                 [compojure "1.6.1" :exclusions
                  [org.clojure/clojure org.clojure/clojure-contrib]]
                 [org.clojure/clojurescript "1.10.520"]]
  :plugins [[lein-cljsbuild "1.1.7"]
            [lein-ring "0.7.0"]
            [cider/cider-nrepl "0.21.1"]
            [lein-codox "0.10.7"]]
  :source-paths ["src"]
  :resource-paths ["resources"]
  :codox {:output-path "resources/public/doc/api"}
  :cljsbuild {:repl-listen-port 9000
              :crossovers [crossover .]
              :crossover-jar true
              :builds {:release
                       {:source-paths ["src/client"]
                        :compiler {:output-to "resources/public/release.js"
                                   :optimizations :advanced
                                   :pretty-print false}}
                       :debug
                       {:source-paths ["src/client"]
                        :compiler {:output-to "resources/public/debug.js"
                                   :optimizations :whitespace
                                   :pretty-print true}}}}
  :profiles {:uberjar {:aot :all
                       :hooks [leiningen.cljsbuild]
                       :prep-tasks ["javac" "compile" "codox"]}}
  :main server.core)
