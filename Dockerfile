# Declare the base image - here is a light weight JDK 8 environment setup
FROM openjdk:8-jdk-alpine

# Copy the jar produced from the mvn clean package phase from the target to the inside of the container
COPY /target/TheAvengersLeague-0.0.1-SNAPSHOT.war TheAvengersLeague-0.0.1-SNAPSHOT.war

# Expose port 8080 of the container
EXPOSE 8080

# Run the JAR when you run the container, thus executing the app
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "/TheAvengersLeague-0.0.1-SNAPSHOT.war"]