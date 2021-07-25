FROM openjdk:11
EXPOSE 8081
COPY target/*.jar core.jar
ENTRYPOINT ["java", "-jar", "/core.jar"]