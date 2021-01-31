package es.bnext.api.error;

import io.micronaut.context.annotation.Requires;
import io.micronaut.core.convert.value.ConvertibleValues;
import io.micronaut.http.HttpParameters;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;

import javax.inject.Singleton;
import java.util.Optional;

@Produces
@Singleton
@Requires(classes = {ExceptionHandler.class, Throwable.class})
public class GlobalExceptionHandler implements ExceptionHandler<Throwable, HttpResponse> {
    @Override
    public HttpResponse handle(HttpRequest request, Throwable exception) {
        return HttpResponse.ok(createResponse(Errors.UNKNOWN, "unknown", request.getBody(), request.getParameters(), exception, null));
    }

    public ErrorDTO createResponse(Errors code, String description, Optional possibleBody, HttpParameters parameters, Throwable exception, Object details) {
        return ErrorDTO.builder().code(code).description(description).body(possibleBody.orElse(null)).parameters(
                Optional.ofNullable(parameters).map(
                        ConvertibleValues::asMap).orElse(null)).exception(exception).details(details).build();
    }

    public Object createResponse(ErrorDTO errorDTO, Optional possibleBody, HttpParameters parameters) {
        return createResponse(errorDTO.getCode(), errorDTO.getDescription(), possibleBody, parameters, errorDTO.getException(),
                errorDTO.getDetails());
    }
}
