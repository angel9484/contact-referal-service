package es.bnext.api.dto.contact;

import es.bnext.api.dto.user.UserDTO;
import io.micronaut.core.annotation.Introspected;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Introspected
@Schema(name = "UserContacts", description = "Details of the user to be registered.")
@NoArgsConstructor
@AllArgsConstructor
public class UserContactsDTO {
    private String phone;
    private UserDTO userDTO;
    private List<ContactDTO> contacts;
}
