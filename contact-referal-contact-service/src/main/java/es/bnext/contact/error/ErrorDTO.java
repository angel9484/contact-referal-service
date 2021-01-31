package es.bnext.contact.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ErrorDTO {
    private Errors code;
    private String description;
    private Object body;
    private Map<String, List<String>> parameters;
    private Throwable exception;
    private Object details;

}
