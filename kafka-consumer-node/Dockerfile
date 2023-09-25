FROM amazoncorretto:11-alpine-jdk
MAINTAINER fajri.com
COPY build/libs/kafka-consumer-node-0.0.1-SNAPSHOT.jar docker-kafka-consumer-node.jar
ENTRYPOINT ["java","-jar","/docker-kafka-consumer-node.war"]