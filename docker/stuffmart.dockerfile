FROM openjdk:8-jre-alpine

RUN mkdir -p /opt/stuffmart

COPY stuffmart-0.1.0.jar /opt/stuffmart

WORKDIR /opt/stuffmart
CMD java -jar stuffmart-0.1.0.jar
