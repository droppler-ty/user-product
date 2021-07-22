FROM openjdk:11
EXPOSE 8080
COPY target/*.jar dockerKafka.jar
ENTRYPOINT ["java", "-jar", "/dockerKafka.jar"]