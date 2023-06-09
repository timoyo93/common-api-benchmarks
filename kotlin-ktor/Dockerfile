FROM gradle:jdk17 AS build
WORKDIR /source
COPY . .
RUN gradle buildFatJar --no-daemon

FROM debian:11.6-slim
RUN apt-get update && apt-get install -y openjdk-17-jre
COPY --from=build /source/build/libs/kotlin-ktor-all.jar ./kotlin-ktor.jar
EXPOSE 8050

ENTRYPOINT ["java", "-jar", "kotlin-ktor.jar"]
