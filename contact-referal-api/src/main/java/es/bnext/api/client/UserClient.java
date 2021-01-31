package es.bnext.api.client;

import es.bnext.api.dto.user.UserCreationDTO;
import es.bnext.api.dto.user.UserDTO;
import es.bnext.api.dto.user.UserPhonesSearchDTO;
import es.bnext.api.error.ErrorDTO;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;

import java.util.List;

@Client(value = "${client.user.url}", errorType = ErrorDTO.class)
public interface UserClient {
    @Post("/users/findByPhones")
    List<UserDTO> findByPhones(@Body UserPhonesSearchDTO userPhonesSearchDTO);

    @Post("/users/createUser")
    UserDTO createUser(@Body UserCreationDTO userPhonesSearchDTO);
}
