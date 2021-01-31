package es.bnext.contact.error;

import io.micronaut.http.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ApplicationException extends RuntimeException {
    private HttpStatus httpStatus;
    private ErrorDTO errorDTO;
}
