FROM maven:3-openjdk-17 AS builder

WORKDIR /app

COPY . .
RUN mvn clean package

FROM openjdk:17-jdk-slim

WORKDIR /app

ENV LOG_LEVEL_ROOT=INFO
ENV LOG_LEVEL_SPRING=INFO
ENV LOG_LEVEL_APP=DEBUG

ENV CORE_POOL_SIZE=2
ENV MAX_POOL_SIZE=2
#optional
ENV TERM_TIMEOUT_SEC=30

EXPOSE 8080
EXPOSE 8090

COPY --from=builder /app/target/draft-1.0.jar /app/draft-1.0.jar

ENTRYPOINT ["java", "-Xmx4g", "-jar", "/app/draft-1.0.jar"]