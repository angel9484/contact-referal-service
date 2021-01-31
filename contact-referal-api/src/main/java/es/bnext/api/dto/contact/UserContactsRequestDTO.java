package es.bnext.api.dto.contact;

import io.micronaut.core.annotation.Introspected;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Introspected
@Schema(name = "UserHelperContactsRequest", description = "Details of the user to be registered.")
@NoArgsConstructor
@AllArgsConstructor
public class UserContactsRequestDTO {
    private String phone;
    private List<ContactDTO> contacts;
}
