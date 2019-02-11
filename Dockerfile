FROM maven as builder
WORKDIR /app
COPY . /app
RUN mvn package

FROM openjdk:13-alpine
MAINTAINER ipcrm
WORKDIR /app
COPY --from=builder /app/java_webapp/target/java-webapp-*.jar java-webapp-*.jar
CMD java -jar java-webapp-*.jar
EXPOSE 9999
