FROM gradle:6.8.1-jdk11 as builder

WORKDIR /project/
COPY settings.gradle settings.gradle
COPY gradle.properties gradle.properties

WORKDIR /project/contact-referal-contact-service/
COPY contact-referal-contact-service/src src
COPY contact-referal-contact-service/build.gradle build.gradle
COPY contact-referal-contact-service/openapi.properties openapi.properties
COPY contact-referal-contact-service/micronaut-cli.yml micronaut-cli.yml

WORKDIR /project/

RUN gradle assembleShadowDist

FROM openjdk:11.0-jre
WORKDIR /project
COPY --from=builder /project/contact-referal-contact-service/build/libs/contact-referal-contact-service-0.1-all.jar app.jar
ENTRYPOINT java -jar app.jar
