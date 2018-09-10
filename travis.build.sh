#!/usr/bin/env bash
mvn clean test -B
mvn install -DskipTests=true -Dmaven.javadoc.skip=true -B -V
