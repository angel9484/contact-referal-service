package es.bnext.contact.dto;

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
@Schema(name = "UserSearch", description = "list of user ids to search their contacts.")
public class UserContactsSearchDTO {
    @Schema(description = "list of the user ids", minimum = "1")
    @Min(value = 1)
    @NotNull
    @NotEmpty
    private List<Integer> userIds;
}
