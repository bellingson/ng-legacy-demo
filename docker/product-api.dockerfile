FROM openjdk:8-jre-alpine

RUN mkdir -p /opt/product-api

COPY product-api-0.1.0.jar /opt/product-api

WORKDIR /opt/product-api
CMD java -jar product-api-0.1.0.jar
