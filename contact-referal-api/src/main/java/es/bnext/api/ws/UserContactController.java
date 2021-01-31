package es.bnext.api.ws;

import es.bnext.api.dto.contact.ContactDTO;
import es.bnext.api.dto.user.UserDTO;
import es.bnext.api.dto.user.UserPhonesSearchDTO;
import es.bnext.api.error.ErrorDTO;
import es.bnext.api.service.UserContactService;
import io.micronaut.core.version.annotation.Version;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.validation.Validated;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import javax.validation.Valid;
import java.util.List;

@Controller("/users")
@RequiredArgsConstructor
@Validated
public class UserContactController {
    private final UserContactService userContactService;

    @Version(value = "1")
    @Operation(summary = "Get users details",
               description = "Get the intersection of the contacts of two users.")
    @ApiResponse(responseCode = "200", description = "List of users that owns this phones",
                 content = @Content(mediaType = MediaType.APPLICATION_JSON,
                                    array = @ArraySchema(schema = @Schema(implementation = UserDTO.class))))
    @ApiResponse(responseCode = "204", description = "The size of the list returned is not the same than the size of the users requested.",
                 content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ErrorDTO.class)))
    @Tag(name = "user-contacts")
    @Post(produces = MediaType.APPLICATION_JSON, value = "/getCommonContacts")
    public List<ContactDTO> getCommonContacts(@Body @Valid UserPhonesSearchDTO userPhonesSearchDTO) {
        return userContactService.getCommonContacts(userPhonesSearchDTO);
    }
}
