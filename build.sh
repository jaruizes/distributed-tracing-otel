#!/bin/bash
set -e

ROOT_FOLDER="$(pwd)"

removeLastDockerImages() {
  docker rmi node/service-a:latest spring/service-b:latest quarkus/service-c:latest quarkus/service-d:latest --force
}

buildServiceA() {
  cd "$ROOT_FOLDER"/service-a
  sh build.sh
}

buildServiceB() {
  cd "$ROOT_FOLDER"/service-b
  sh build.sh
}

buildServiceC() {
  cd "$ROOT_FOLDER"/service-c
  sh build.sh
}

buildServiceD() {
  cd "$ROOT_FOLDER"/service-d
  sh build.sh
}

removeLastDockerImages
buildServiceA
buildServiceB
buildServiceC
buildServiceD
