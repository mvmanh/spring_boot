# Use a base image with OpenJDK 11
FROM openjdk:17-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file into the container at /app
COPY target/app-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose port 8080 to the outside world
EXPOSE 8080

# Define environment variables
ENV JAVA_OPTS=""

# Run the JAR file when the container launches
ENTRYPOINT ["java", "-jar", "app.jar"]
