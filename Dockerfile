FROM adoptopenjdk/openjdk11:alpine-jre
ARG JAR_FILE=target/shorturl-0.0.2-SNAPSHOT.jar
COPY ${JAR_FILE} shortApp.jar
ENTRYPOINT ["java","-jar","/shortApp.jar"]

