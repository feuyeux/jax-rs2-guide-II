#FROM java:8
FROM index.tenxcloud.com/docker_library/java
VOLUME /tmp
ADD spring-boot-docker-1.0.0.jar my_app.jar
RUN bash -c 'touch /my_app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/my_app.jar"]