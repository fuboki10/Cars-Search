FROM openjdk:17-jdk-slim
WORKDIR app
COPY target/*.jar cars.jar
CMD ["java", "-jar", "cars.jar"]