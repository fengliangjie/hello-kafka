#!/bin/bash
echo 'Removing Zookeeper and Kafka by docker-compose if exists...'
docker-compose down;
echo 'Running Zookeeper and Kafka by docker-compose...';
docker-compose up -d;
echo done,Click any key to continue....
read -n 1