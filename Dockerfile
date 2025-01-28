FROM openjdk:17

COPY target/marketplace-0.0.1-SNAPSHOT.jar app.jar

COPY src/main/resources/json /resources/json

ENTRYPOINT ["java", "-jar", "app.jar"]