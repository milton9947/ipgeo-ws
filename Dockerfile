FROM adoptopenjdk/openjdk11:latest
RUN mkdir /usr/src/ipgeo-ws
COPY build/libs/ipgeo-ws-0.0.1-SNAPSHOT.jar /usr/src/ipgeo-ws
WORKDIR /usr/src/ipgeo-ws
EXPOSE 8080
CMD ["java", "-jar", "/usr/src/ipgeo-ws/ipgeo-ws-0.0.1-SNAPSHOT.jar"]