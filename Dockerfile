FROM amazoncorretto:24-al2023-jdk
ARG JAR_FILE=target/*.jar
COPY demo/build/libs/demo-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
