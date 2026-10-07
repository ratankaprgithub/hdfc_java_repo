FROM amazoncorretto:21

WORKDIR /app

COPY target/product-service-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8082

CMD ["java","-jar","app.jar"]