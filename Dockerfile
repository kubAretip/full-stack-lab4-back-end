# STAGE 1 - Budowanie projektu gradle -> zbudowanie pliku jar
FROM gradle:jdk11 as builder
# Klonowanie projektu z gita
RUN git clone https://github.com/kubAretip/full-stack-lab4-back-end.git
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

# STAGE 2 - Uruchominie aplikacji
FROM openjdk:11.0-jre-slim
EXPOSE 8080
RUN mkdir /app
# Skopiowanie paczki z poprzedniego stage
COPY --from=builder /home/gradle/src/build/libs/*.jar /app/rest-api.jar
CMD ["java","-jar","/app/rest-api.jar"]