FROM openjdk:17-jdk-slim

RUN apt-get update && apt-get install -y libcurl4 && rm -rf /var/lib/apt/lists/*

RUN mkdir /app

COPY build/libs/pinapp-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
