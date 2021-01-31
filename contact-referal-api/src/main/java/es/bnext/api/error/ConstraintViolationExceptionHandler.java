package es.bnext.api.error;

import io.micronaut.context.annotation.Primary;
import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.hateoas.JsonError;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import io.micronaut.jackson.JacksonConfiguration;
import io.micronaut.validation.exceptions.ConstraintExceptionHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import javax.validation.ConstraintViolationException;

@Singleton
@Requires(classes = {ExceptionHandler.class, ConstraintViolationException.class})
@RequiredArgsConstructor
@Primary
@Slf4j
public class ConstraintViolationExceptionHandler implements ExceptionHandler<ConstraintViolationException, HttpResponse> {
    private final GlobalExceptionHandler globalExceptionHandler;
    private ConstraintExceptionHandler constraintExceptionHandler;

    @PostConstruct
    public void init(JacksonConfiguration jacksonConfiguration) {
        this.constraintExceptionHandler = new ConstraintExceptionHandler(jacksonConfiguration);
    }

    @Override
    public HttpResponse handle(HttpRequest request, ConstraintViolationException exception) {
        HttpResponse<JsonError> handle = constraintExceptionHandler.handle(request, exception);
        return HttpResponse.status(HttpStatus.valueOf(handle.code())).body(
                globalExceptionHandler.createResponse(Errors.VALIDATION_ERROR, handle.reason(), request.getBody(), request.getParameters(),
                        null, handle.body()));
    }
}
