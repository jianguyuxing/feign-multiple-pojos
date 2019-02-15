FROM jdk8
VOLUME /tmp
ADD eureka.jar eureka.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/eureka.jar"]