FROM openjdk:11.0-jre
COPY build/libs/contact-referal-contact-service-0.1-all.jar app.jar
ENTRYPOINT java -jar app.jar
