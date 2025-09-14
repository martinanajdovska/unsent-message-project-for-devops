FROM maven:3.9-amazoncorretto-21 AS build-stage

WORKDIR /app

COPY pom.xml ./
COPY src ./src

RUN mvn package spring-boot:repackage -DskipTests

FROM amazoncorretto:21-alpine-jdk AS run-stage

COPY --from=build-stage /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]