FROM openjdk:8
LABEL maintainer="customerservice"
ADD target/customer-service-0.0.1-SNAPSHOT.jar customer-service.jar
ENTRYPOINT ["java","-jar","customer-service.jar"]
