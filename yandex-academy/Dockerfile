FROM openjdk:17.0.1-jdk-slim

COPY target/enrollment-0.0.1-SNAPSHOT.jar /app/
WORKDIR /app

ENTRYPOINT ["java", "-jar", "enrollment-0.0.1-SNAPSHOT.jar"]
