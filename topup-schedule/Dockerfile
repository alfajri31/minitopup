FROM amazoncorretto:11-alpine-jdk
MAINTAINER fajri.com
COPY build/libs/topup-schedule-0.0.1-SNAPSHOT.jar docker-topup-schedule.jar
ENTRYPOINT ["java","-jar","/docker-topup-schedule.jar"]