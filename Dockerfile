FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY ./target/spring-boot-mysql.jar /usr/app/
WORKDIR /usr/app
EXPOSE 8080
CMD ["java", "-jar", "spring-boot-mysql.jar"]
