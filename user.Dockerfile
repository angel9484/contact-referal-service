FROM gradle:6.8.1-jdk11 as builder

WORKDIR /project/
COPY settings.gradle settings.gradle
COPY gradle.properties gradle.properties

WORKDIR /project/contact-referal-user-service/
COPY contact-referal-user-service/src src
COPY contact-referal-user-service/build.gradle build.gradle
COPY contact-referal-user-service/openapi.properties openapi.properties
COPY contact-referal-user-service/micronaut-cli.yml micronaut-cli.yml

WORKDIR /project/

RUN gradle assembleShadowDist

FROM openjdk:11.0-jre
WORKDIR /project
COPY --from=builder /project/contact-referal-user-service/build/libs/contact-referal-user-service-0.1-all.jar app.jar
ENTRYPOINT java -jar app.jar
