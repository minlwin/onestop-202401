FROM amazoncorretto:21-alpine-jdk

RUN apk update && apk add --no-cache tzdata

RUN addgroup -S spring && adduser -S spring -G spring 

USER spring:spring

ENV TZ=Asia/Yangon

COPY target/*.jar app.jar

EXPOSE 8080/tcp

ENTRYPOINT ["java","-jar", "/app.jar"]