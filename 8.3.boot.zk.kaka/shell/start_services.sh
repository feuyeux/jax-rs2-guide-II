#!/usr/bin/env bash
sed -r -i "s/(spring.cloud.zookeeperconnectString)=(.*)/\1=${ZK}/g" /opt/zk.kaka.properties
sed -r -i "s/(zkConnect)=(.*)/\1=${ZK}/g" /opt/zk.kaka.properties
sed -r -i "s/(kafkaServerList)=(.*)/\1=${KAFKA}/g" /opt/zk.kaka.properties
java -Djava.security.egd=file:/dev/./urandom -jar /my_app.jar