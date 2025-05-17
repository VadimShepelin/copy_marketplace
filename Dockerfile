FROM openjdk:17

COPY target/*.jar app.jar

COPY src/main/resources/json /resources/json

ENTRYPOINT ["java", "-jar", "app.jar"]