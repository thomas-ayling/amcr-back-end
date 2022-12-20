# syntax=docker/dockerfile:1

FROM amazoncorretto:17

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:resolve

COPY src ./src

EXPOSE 3001

CMD ["./mvnw", "spring-boot:run"]