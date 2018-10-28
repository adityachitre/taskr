FROM openjdk:8-jdk-alpine
LABEL maintainer chitreaditya@gmail.com
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=build/libs/taskr-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} taskr.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/taskr.jar"]
