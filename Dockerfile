FROM maven as builder
WORKDIR /app
COPY . /app
RUN mvn package

FROM openjdk:13-alpine
MAINTAINER ipcrm
WORKDIR /app
COPY --from=builder /app/java_webapp/target/java-webapp-1.4.jar java-webapp-1.4.jar
CMD java -jar java-webapp-1.4.jar
EXPOSE 9999