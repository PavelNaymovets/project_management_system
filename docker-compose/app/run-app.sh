#!/bin/sh

mvn clean install -f ../../ -DskipTests=true
docker build -t pms-app:latest ../../ --file Dockerfile
cd ../
docker-compose up