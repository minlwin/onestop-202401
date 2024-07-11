FROM amazoncorretto:21-alpine-jdk

RUN apk update && apk add --no-cache tzdata

RUN addgroup -S spring \
    && adduser -S spring -G spring \
    && mkdir -p /var/lib/storage/profile \
    && mkdir -p /var/log/application \
    && chown -R spring:spring /var/lib/storage/profile \
    && chown -R spring:spring /var/log/application

USER spring:spring

ENV TZ=Asia/Yangon

COPY target/*.jar app.jar

EXPOSE 8080/tcp

ENTRYPOINT ["java","-jar", "/app.jar"]