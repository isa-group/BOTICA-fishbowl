FROM openjdk:11

WORKDIR /app
COPY target/fishbowl.jar /app/fishbowl.jar

CMD ["java", "-jar", "/app/fishbowl.jar"]
