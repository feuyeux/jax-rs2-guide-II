#!/usr/bin/env bash
echo "ZK=${ZK}"
echo "KAFKA=${KAFKA}"
sed -r -i "s/(spring.cloud.zookeeper.connectString)=(.*)/\1=${ZK}/g" /opt/zk.kaka.properties
sed -r -i "s/(zkConnect)=(.*)/\1=${ZK}/g" /opt/zk.kaka.properties
sed -r -i "s/(kafkaServerList)=(.*)/\1=${KAFKA}/g" /opt/zk.kaka.properties
echo "zk.kaka.properties:"
echo "===="
cat /opt/zk.kaka.properties
echo "===="
echo "start micro services..."
java -Djava.security.egd=file:/dev/./urandom -Dspring.cloud.bootstrap.location=/opt/zk.kaka.properties -jar /my_app.jar