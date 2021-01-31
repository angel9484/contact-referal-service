FROM gradle:6.8.1-jdk11 as builder

WORKDIR /project/
COPY settings.gradle settings.gradle
COPY gradle.properties gradle.properties

WORKDIR /project/contact-referal-api/
COPY contact-referal-api/src src
COPY contact-referal-api/build.gradle build.gradle
COPY contact-referal-api/openapi.properties openapi.properties
COPY contact-referal-api/micronaut-cli.yml micronaut-cli.yml

WORKDIR /project/

RUN gradle assembleShadowDist

FROM openjdk:11.0-jre
WORKDIR /project
COPY --from=builder /project/contact-referal-api/build/libs/contact-referal-api-0.1-all.jar app.jar
ENTRYPOINT java -jar app.jar
