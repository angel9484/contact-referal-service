package es.bnext.api.dto.contact;

import io.micronaut.core.annotation.Introspected;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Introspected
@Schema(name = "Contact", description = "Contact details.")
@NoArgsConstructor
@AllArgsConstructor
public class ContactDTO {
    private String contactName;
    private String phone;
}
