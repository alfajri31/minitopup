FROM amazoncorretto:11-alpine-jdk
MAINTAINER fajri.com
COPY build/libs/topup-api-0.0.1-SNAPSHOT.jar docker-topup-api.jar
ENTRYPOINT ["java","-jar","/docker-topup-api.jar"]