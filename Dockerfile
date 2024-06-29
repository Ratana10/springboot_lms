FROM openjdk:17-alpine as build
LABEL authors="Ratana"

COPY target/leanring-management-system-0.0.1-SNAPSHOT.jar leanring-management-system-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "/leanring-management-system-0.0.1-SNAPSHOT.jar"]