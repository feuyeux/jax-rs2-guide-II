#!/usr/bin/env bash
BASE=/Users/erichan/sourcecode/feuyeux/jax-rs2-guide-II/7.3.boot.etcd
cd ${BASE}
mvn clean install -DskipTests
export SERVER_PORT=8080 && nohup java -jar ${BASE}/target/boot.etcd-0.0.1-SNAPSHOT.jar &
export SERVER_PORT=8081 && nohup java -jar ${BASE}/target/boot.etcd-0.0.1-SNAPSHOT.jar &
export SERVER_PORT=8082 && nohup java -jar ${BASE}/target/boot.etcd-0.0.1-SNAPSHOT.jar &
cp ${BASE}/shell/nginx_services0.conf ${BASE}/shell/nginx_services.conf
nginx -c ${BASE}/shell/nginx_services.conf