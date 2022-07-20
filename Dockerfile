FROM adoptopenjdk/openjdk11
EXPOSE 8080
ARG JAR_FILE=target/bookseller-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} bookseller_application.jar
ENTRYPOINT ["java", "-jar", "/bookseller_application.jar"]
