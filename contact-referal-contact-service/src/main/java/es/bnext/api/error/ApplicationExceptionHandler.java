package es.bnext.api.error;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Singleton;

@Singleton
@Requires(classes = {ExceptionHandler.class, ApplicationException.class})
@RequiredArgsConstructor
@Slf4j
public class ApplicationExceptionHandler implements ExceptionHandler<ApplicationException, HttpResponse> {
    private final GlobalExceptionHandler globalExceptionHandler;

    @Override
    public HttpResponse handle(HttpRequest request, ApplicationException exception) {
        return HttpResponse.status(exception.getHttpStatus()).body(globalExceptionHandler.createResponse(exception.getErrorDTO(),
                request.getBody(), request.getParameters()));
    }
}
