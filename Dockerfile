FROM eclipse-temurin:17-jdk-alpine AS build
WORKDIR /workspace/app

COPY gradlew ./
COPY gradle/ gradle/
RUN ./gradlew --version

COPY . .
RUN ./gradlew --no-daemon bootJar

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /workspace/app/build/libs/quizzer-backend.jar ./
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/quizzer-backend.jar"]