FROM eclipse-temurin:21-jdk

COPY target/day_1_practice-0.0.1-SNAPSHOT.jar app.jar


ENTRYPOINT ["java","-jar","/app.jar"]