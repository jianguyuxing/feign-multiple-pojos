FROM jdk8
VOLUME /tmp
ADD producer.jar producer.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/producer.jar"]