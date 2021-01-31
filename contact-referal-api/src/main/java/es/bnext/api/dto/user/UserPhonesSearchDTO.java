package es.bnext.api.dto.user;

import io.micronaut.core.annotation.Introspected;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Introspected
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "UserSearch", description = "list of phones to search their users.")
public class UserPhonesSearchDTO {
    @Schema(description = "list of the phones", minimum = "1")
    @Min(value = 1)
    @NotNull
    @NotEmpty
    private List<String> phones;
}
