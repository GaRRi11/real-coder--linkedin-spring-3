FROM maven:3-openjdk-17-slim AS build

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline -B

COPY src ./src

RUN mvn clean package --no-snapshot-updates

FROM maven:3-openjdk-17-slim

EXPOSE 8080

COPY --from=build /app/target/real-coder-linkedin-spring-3-0.0.1-SNAPSHOT.jar real-coder-linkedin-spring-3.jar

ENTRYPOINT ["java","-jar","real-coder-linkedin-spring-3.jar"]
