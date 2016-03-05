#!/usr/bin/env bash
BASE=/Users/erichan/sourcecode/feuyeux/jax-rs2-guide-II/7.3.boot.consul
cd ${BASE}
nohup java -jar ${BASE}/target/boot.consul-0.0.1-SNAPSHOT.jar &
echo $! > boot.consul.8080.pid
sleep 10
curl :8080/hi
sleep 2
curl :8080/hi2
sleep 2
kill -15 `cat boot.consul.8080.pid`