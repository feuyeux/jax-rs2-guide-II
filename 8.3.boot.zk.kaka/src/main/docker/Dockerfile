#FROM java:8
FROM index.tenxcloud.com/docker_library/java
VOLUME /tmp
ADD boot.zk.kaka-0.0.1-SNAPSHOT.jar my_app.jar
ADD /opt/zk.kaka.properties /opt/zk.kaka.properties
ADD /Users/erichan/sourcecode/feuyeux/jax-rs2-guide-II/8.3.boot.zk.kaka/shell/start_services.sh start_services.sh
RUN chmod 777 start_services.sh
RUN bash -c 'touch /my_app.jar'
ENTRYPOINT ["start_services.sh"]