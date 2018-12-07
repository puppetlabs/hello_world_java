FROM openjdk:9
MAINTAINER ipcrm
COPY java_webapp/target/java-webapp-1.4.jar /usr/src/java-webapp-1.5.jar
WORKDIR /usr/src
CMD java -jar java-webapp-1.5.jar
EXPOSE 9999
