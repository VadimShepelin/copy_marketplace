FROM openjdk:17
COPY target/*.jar  mail.jar
ENTRYPOINT ["java", "-jar", "mail.jar"]
CMD ["--spring.config.location=file:application.yml"]
