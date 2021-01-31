package es.bnext.api.ws;

import es.bnext.api.dto.contact.UserContactsDTO;
import es.bnext.api.dto.contact.UserContactsRequestDTO;
import es.bnext.api.dto.contact.UserContactsResponseDTO;
import es.bnext.api.dto.user.UserPhonesSearchDTO;
import es.bnext.api.error.ErrorDTO;
import es.bnext.api.service.ContactService;
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

@Controller("/contacts")
@RequiredArgsConstructor
@Validated
public class ContactController {

    private final ContactService contactService;

    @Version(value = "1")
    @Operation(summary = "Get users contacts details",
               description = "Get a collection of contacts per user id queried with their user ids.")
    @ApiResponse(responseCode = "200", description = "List of users that owns this phones",
                 content = @Content(mediaType = MediaType.APPLICATION_JSON,
                                    array = @ArraySchema(schema = @Schema(implementation = UserContactsDTO.class))))
    @ApiResponse(responseCode = "400", description = "No user id's present",
                 content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ErrorDTO.class)))
    @Tag(name = "contacts")
    @Post(produces = MediaType.APPLICATION_JSON, value = "/findByUserIds")
    public List<UserContactsDTO> findByUserIds(@Body @Valid UserPhonesSearchDTO userPhonesSearchDTO) {
        return contactService.findContactsByUserId(userPhonesSearchDTO);
    }
    //TODO pending activate yet

    @Version(value = "1")
    @Operation(summary = "Create or update contacts for an user",
               description = "Create or update contacts for an user. This will delete all non existent contacts here.")
    @ApiResponse(responseCode = "200", description = "Contact created",
                 content = @Content(mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = UserContactsResponseDTO.class)))
    @Tag(name = "contacts")
    @Post(produces = MediaType.APPLICATION_JSON, value = "/createOrUpdateContacts")
    public UserContactsResponseDTO create(@Valid @Body UserContactsRequestDTO userContactsDTO) {
        return contactService.saveContacts(userContactsDTO);
    }
}
