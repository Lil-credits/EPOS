FROM gradle:7.3.3-jdk17-alpine AS build

# Copy project files
COPY --chown=gradle:gradle . /home/gradle/src

# Set working directory
WORKDIR /home/gradle/src

# Run ShadowJar task
RUN gradle shadowJar --no-daemon


FROM amazoncorretto:17
# Copy the built JAR file from the build stage
COPY --from=build /home/gradle/src/build/libs/portal-api-1.0.0-SNAPSHOT-fat.jar /app/portal-api-1.0.0-SNAPSHOT-fat.jar

# Set the working directory
WORKDIR /app

# Expose necessary ports
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "portal-api-1.0.0-SNAPSHOT-fat.jar"]
