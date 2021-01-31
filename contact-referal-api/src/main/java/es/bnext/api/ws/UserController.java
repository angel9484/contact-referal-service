package es.bnext.api.ws;

import es.bnext.api.dto.user.UserCreationDTO;
import es.bnext.api.dto.user.UserDTO;
import es.bnext.api.dto.user.UserPhonesSearchDTO;
import es.bnext.api.error.ErrorDTO;
import es.bnext.api.service.UserService;
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
public class UserController {

    private final UserService userService;

    //TODO pending activate yet
    @Version(value = "1")
    @Operation(summary = "Get users details",
               description = "Get a subset of users queried with their phones or throws an error if the entire list is not retrieved.")
    @ApiResponse(responseCode = "200", description = "List of users that owns this phones",
                 content = @Content(mediaType = MediaType.APPLICATION_JSON,
                                    array = @ArraySchema(schema = @Schema(implementation = UserDTO.class))))
    @ApiResponse(responseCode = "400", description = "No user id's present",
                 content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ErrorDTO.class)))
    @ApiResponse(responseCode = "204", description = "The size of the list returned is not the same than the size of the users requested.",
                 content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ErrorDTO.class)))
    @Tag(name = "users")
    @Post(produces = MediaType.APPLICATION_JSON, value = "/findByPhones")
    public List<UserDTO> findByPhones(@Body @Valid UserPhonesSearchDTO userPhonesSearchDTO) {
        return userService.findByPhones(userPhonesSearchDTO);
    }

    @Version(value = "1")
    @Operation(summary = "Create a new user",
               description = "Creates a new user")
    @ApiResponse(responseCode = "200", description = "User created",
                 content = @Content(mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = UserDTO.class)))
    @ApiResponse(responseCode = "400", description = "No user id's present",
                 content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ErrorDTO.class)))
    @ApiResponse(responseCode = "500", description = "The size of the list returned is not the same than the size of the users requested.",
                 content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ErrorDTO.class)))
    @Tag(name = "users")
    @Post(produces = MediaType.APPLICATION_JSON, value = "/createUser")
    public UserDTO create(@Valid @Body UserCreationDTO userCreationDTO) {
        return userService.createUser(userCreationDTO);
    }
}
