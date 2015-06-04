#!/bin/bash

docker --help > /dev/null 2>&1

if [ $? != 0 ]; then
  printf "Docker must be installed before running this script!\n"
  exit 1
fi


printf "Dockerizing MongoDB..."
PS="$(docker ps -a | grep mongo)"

if [ ! -z "$PS" ]; then
  docker stop mongo; docker rm mongo
fi

mkdir -p /opt/docker/mongo_volume
docker run --name mongo -v /opt/docker/mongo_volume:/data/db/ -d mongo:3


printf "Dockerizing Clojure application..."
PS="$(docker ps -a | grep clj-app)"

if [ ! -z "$PS" ]; then
  docker stop clj-app; docker rm clj-app
fi

docker build -t clj-app-v1 ./app
docker run -d -p 80:8080 --name clj-app --link mongo:mongo -d clj-app-v1


exit 0
