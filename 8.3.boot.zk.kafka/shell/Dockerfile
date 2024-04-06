#FROM java:8
FROM index.tenxcloud.com/docker_library/java
VOLUME /tmp
ADD boot.zk.kafka-0.0.1-SNAPSHOT.jar my_app.jar
ADD zk.kafka.properties /opt/zk.kafka.properties
ADD start.sh start.sh
RUN chmod 777 start.sh
RUN bash -c 'touch /my_app.jar'
ENTRYPOINT ["/start.sh"]