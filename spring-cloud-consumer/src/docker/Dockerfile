FROM jdk8
VOLUME /tmp
ADD consumer.jar consumer.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/consumer.jar"]