FROM gradle:jdk17 AS build
WORKDIR /source
COPY . .
RUN gradle bootJar --no-daemon

FROM debian:11.6-slim
RUN apt-get update && apt-get install -y openjdk-17-jre
COPY --from=build /source/build/libs/*.jar ./kotlin-spring.jar
EXPOSE 8060

ENTRYPOINT ["java", "-jar", "kotlin-spring.jar"]
