package es.bnext.api.error;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Singleton;

@Singleton
@Requires(classes = {ExceptionHandler.class, HttpClientResponseException.class})
@RequiredArgsConstructor
@Slf4j
public class HttpClientResponseExceptionHandler implements ExceptionHandler<HttpClientResponseException, HttpResponse> {

    @Override
    public HttpResponse handle(HttpRequest request, HttpClientResponseException exception) {
        ErrorDTO errorDTO = (ErrorDTO) exception.getResponse();
        return HttpResponse.status(exception.getStatus()).body(errorDTO);
    }
}
