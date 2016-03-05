#!/usr/bin/env bash
BASE=/Users/erichan/sourcecode/feuyeux/jax-rs2-guide-II/7.3.boot.consul
cd ${BASE}
mvn clean install -DskipTests
nohup java -jar ${BASE}/target/boot.consul-0.0.1-SNAPSHOT.jar &
nohup java -jar ${BASE}/target/boot.consul-0.0.1-SNAPSHOT.jar --server.port=8081 &
nohup java -jar ${BASE}/target/boot.consul-0.0.1-SNAPSHOT.jar --server.port=8082 &
cp ${BASE}/shell/nginx_services0.conf ${BASE}/shell/nginx_services.conf
nginx -c ${BASE}/shell/nginx_services.conf