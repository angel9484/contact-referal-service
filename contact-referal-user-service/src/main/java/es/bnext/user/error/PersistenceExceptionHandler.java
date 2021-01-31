package es.bnext.user.error;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Singleton;
import javax.persistence.PersistenceException;
import java.util.Optional;

@Singleton
@Requires(classes = {ExceptionHandler.class, PersistenceException.class})
@RequiredArgsConstructor
@Slf4j
public class PersistenceExceptionHandler implements ExceptionHandler<PersistenceException, HttpResponse> {
    private final GlobalExceptionHandler globalExceptionHandler;

    @Override
    public HttpResponse handle(HttpRequest request, PersistenceException exception) {
        Optional<String> duplicatedEntity = isDuplicatedEntity(exception);
        if (duplicatedEntity.isPresent()) {
            return HttpResponse.badRequest(
                    globalExceptionHandler.createResponse(Errors.DUPLICATED_ENTITY, duplicatedEntity.get(), request.getBody(),
                            request.getParameters(), null, null));
        } else {
            return HttpResponse.serverError(globalExceptionHandler.createResponse(Errors.UNKNOWN, "unknown", request.getBody(),
                    request.getParameters(), exception, null));
        }

    }

    private Optional<String> isDuplicatedEntity(PersistenceException exception) {
        try {
            Throwable internalCause = exception.getCause().getCause();
            if (internalCause.getMessage().contains("Duplicate")) {
                return Optional.of(internalCause.getMessage());
            }
        } catch (Exception e) {
            log.error("Upper level exception not captured in code: {}", exception, e);
        }
        return Optional.empty();
    }
}
