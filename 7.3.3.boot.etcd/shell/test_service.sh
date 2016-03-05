#!/usr/bin/env bash
BASE=/Users/erichan/sourcecode/feuyeux/jax-rs2-guide-II/7.3.boot.etcd
cd ${BASE}
mvn clean install -DskipTests
nohup java -jar ${BASE}/target/boot.etcd-0.0.1-SNAPSHOT.jar &
echo $! > boot.etcd.8080.pid
sleep 10
curl :8080/hi
sleep 2
curl :8080/hi2
sleep 2
kill -15 `cat boot.etcd.8080.pid`