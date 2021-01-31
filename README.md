# Contact Referal Service

## Servicios

- contact-referal-user-service: Se encarga de mantener la base de datos de usuarios (ahora sólo 4 campos)
- contact-referal-contact-service: Se encarga de mantener la base de datos de contactos (pocas operaciones aún)
- contact-referal-api: Comunica con los dos servicios anteriores. Supone que las entradas van a ser siempre teléfonos y no ID's.

## Asunciones

- Pruebas unitarias: Solo usadas en contact-referal-user-service, al tratarse de una prueba y con motivo de acelerar sólo se meten en ese
  servicio.
- Pruebas de integración: Igual que pruebas unitarias.
- Documentación: Se trata de dejarlo tipo "Clean code" metodos autoexplicativos y solo documentación en los Controller.
- Versionado: "Preparado" pero no activado.
- Gestión de errores: Copiado y pegado el sistema de gestion a través de exception handlers, lo suyo sería en una librería y un par de "
  starters" (jerga Spring, lo siento :))
- Solo se harán validaciones simples y concretamente de forma especifica la del teléfono en el estándar especificado en la web que se
  provee.
- Faltan otras cosillas como los endpoints de health y su configuración, etc, ahora el servicio en docker se reinicia hasta que encuentra
  sus dependencias levantadas.
- Logging y metricas, no hay nada.
- Algo me dejaré, pero creo plasmar la idea :)
- Todo en un repo para simplificar, lo suyo seria cada uno en su repo, y asi además la lib de errores sería una realidad muy sencilla de
  hacer.
- La autenticación debe ir a la API pero no está hecha para simplificar la prueba.

## Comentarios

- Me he centrado especialmente en la arquitectura
- Dado que he tenido que aprender el framework (pinceladas de él más bien) habrán usos posiblemente muy primitivos de algunos componentes (
  por ejemplo el Repository que accede a BD me lo he picado yo:))
- He tenido que aprender sobre gradle (5 años sin usar practicamente :P), mockito, los motores "autogeneradores" de micronaut, etc. Ha sido
  divertido :D

## Ejecución

### Urls

- contact-referal-user-service: http://localhost:8080 (http://localhost:8080/swagger/views/swagger-ui)
- contact-referal-contact-service: http://localhost:8081 (http://localhost:8081/swagger/views/swagger-ui)
- contact-referal-api: http://localhost:8082 (http://localhost:8082/swagger/views/swagger-ui)

### Instrucciones

#### Local

- En todos usar el IDE para ejecutar directamente los "Application"
- Otra alternativa es compilar con gradle y arrancar el jar/servicio generado.
- Es necesario el uso de bases de datos, se puede usar el docker-compose -f docker-compose-db.yml up (que levantara dos puertos de mysql
  donde se esperan en el application.yml)

#### Docker

- Se encuentra expuesto el mismo puerto que en local, usar ip o host donde resida docker (puede ser localhost o una ip generada por el
  gestor de maquinas virtuales)
- Si se ha compilado previamente con gradle assemble, usar docker-compose -f docker-compose-app-compiled.yml up (es sustancialmente más
  rapido)
- Sino, usar docker-compose -f docker-compose-app.yml up (ya que usa multistagging y se encargará de compilar cada proyecto)

#### Kubernetes

- No hecho aún pero si se requiere se puede proveer de un chart (helm), en este caso es necesario el correcto uso de kubectl port-forward o
  tener un ingress configurado.

# Documentación autogenerada por micronaut

## Micronaut 2.3.0 Documentation

- [User Guide](https://docs.micronaut.io/2.3.0/guide/index.html)
- [API Reference](https://docs.micronaut.io/2.3.0/api/index.html)
- [Configuration Reference](https://docs.micronaut.io/2.3.0/guide/configurationreference.html)
- [Micronaut Guides](https://guides.micronaut.io/index.html)

---

## Feature testcontainers documentation

- [https://www.testcontainers.org/](https://www.testcontainers.org/)

## Feature http-client documentation

- [Micronaut HTTP Client documentation](https://docs.micronaut.io/latest/guide/index.html#httpClient)

## Feature management documentation

- [Micronaut Management documentation](https://docs.micronaut.io/latest/guide/index.html#management)

## Feature lombok documentation

- [Micronaut Project Lombok documentation](https://docs.micronaut.io/latest/guide/index.html#lombok)

- [https://projectlombok.org/features/all](https://projectlombok.org/features/all)

## Feature jdbc-hikari documentation

- [Micronaut Hikari JDBC Connection Pool documentation](https://micronaut-projects.github.io/micronaut-sql/latest/guide/index.html#jdbc)

## Feature tracing-jaeger documentation

- [Micronaut Jaeger Tracing documentation](https://docs.micronaut.io/latest/guide/index.html#jaeger)

- [https://www.jaegertracing.io](https://www.jaegertracing.io)

## Feature netflix-hystrix documentation

- [Micronaut Netflix Hystrix documentation](https://docs.micronaut.io/latest/guide/index.html#netflixHystrix)

