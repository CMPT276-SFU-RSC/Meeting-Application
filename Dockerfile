FROM maven:3.9-sapmachine-21 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:21-jdk-slim
COPY --from=build /target/sfursmeetingapplication-0.0.1-SNAPSHOT.jar assignment2.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "assignment2.jar"]