# 1단계: 빌드
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# 2단계: 런타임
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/online_community-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
