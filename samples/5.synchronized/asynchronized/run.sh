#!/usr/bin/env bash
mvn clean install -Dmaven.test.skip=true
java -jar -Xms2048m -Xmx2048m -XX:NewSize=256m -XX:MaxNewSize=256m target/asynchronized-0.0.1-SNAPSHOT.jar