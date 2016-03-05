
mvn package && java -jar target/spring-boot-docker-1.0.0.jar
mvn package docker:build
docker run -p 18080:8080 -d feuyeux/spring-boot-docker
curl $(docker-machine ip):18080/hi
["hello spring boot","hello docker"]


