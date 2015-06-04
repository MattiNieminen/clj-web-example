#!/bin/bash

sudo docker --help > /dev/null 2>&1

if [ $? != 0 ]; then
  printf "Docker must be installed before running this script!\n"
  exit 1
fi


printf "Building uberjar...\n"
cd app
lein build
cd ..


printf "Dockerizing MongoDB...\n"
PS="$(sudo docker ps -a | grep mongo)"

if [ ! -z "$PS" ]; then
  sudo docker stop mongo; sudo docker rm mongo
fi

sudo mkdir -p /opt/docker/mongo_volume
sudo docker run --name mongo -v /opt/docker/mongo_volume:/data/db/ -d mongo:3


printf "Dockerizing Clojure application...\n"
PS="$(sudo docker ps -a | grep clj-app)"

if [ ! -z "$PS" ]; then
  sudo docker stop clj-app; sudo docker rm clj-app
fi

sudo docker build -t clojure/app:v1 ./app
sudo docker run -d -p 80:8080 --name clj-app --link mongo:mongo -d clojure/app:v1


exit 0
