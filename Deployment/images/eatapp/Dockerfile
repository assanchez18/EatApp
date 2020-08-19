#FROM maven:3-alpine AS build-project
#ADD ./*.jar target/
#WORKDIR /target
#RUN mvn clean install

FROM openjdk:8-jre-alpine
EXPOSE 8080
WORKDIR /app
COPY EatApp*.jar ./EatApp.jar
CMD ["java", "-jar", "EatApp.jar"]
