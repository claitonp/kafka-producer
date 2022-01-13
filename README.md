Instalando o kafka no docker
===

[Git clone as imagens do kafka](https://github.com/confluentinc/cp-docker-images)


Após clonado, navegue até a pasta

- Single Node: cp-docker-images/examples/kafka-single-node
- Cluster: cp-docker-images/examples/kafka-cluster

docker-compose up -d

docker-compose ps

docker-compose logs zookeeper | grep -i binding

docker-compose logs kafka | grep -i started