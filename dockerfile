FROM eclipse-temurin:17-jdk-jammy
VOLUME /tmp
EXPOSE 8080
COPY edu5.jar edu5.jar
ENTRYPOINT ["java", "-jar","/edu5.jar"]