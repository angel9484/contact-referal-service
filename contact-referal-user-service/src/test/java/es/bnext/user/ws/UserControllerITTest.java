package es.bnext.user.ws;

import com.google.common.collect.Lists;
import es.bnext.user.dto.UserCreationDTO;
import es.bnext.user.dto.UserDTO;
import es.bnext.user.dto.UserPhonesSearchDTO;
import es.bnext.user.error.ErrorDTO;
import es.bnext.user.error.Errors;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@MicronautTest
class UserControllerITTest {

    @Inject
    @Client("/users")
    HttpClient client;

    @Test
    void testFindByPhones_when_result_is_not_same_than_size_should_return_404() {
        HttpResponse<ErrorDTO> phones = client.toBlocking().exchange(
                HttpRequest.POST("/findByPhones",
                        new UserPhonesSearchDTO(Lists.newArrayList("1234", "2345"))), ErrorDTO.class);
        assertEquals(Errors.INCOMPLETE_RESULT_ERROR, phones.getBody().get().getCode());
    }

    @Test
    void testFindByPhones_when_ok_should_return_list_of_users() {
        HttpResponse<UserDTO> userCreated = client.toBlocking().exchange(
                HttpRequest.POST("/createUser", new UserCreationDTO("john", "doe", "12345")), UserDTO.class);
        assertNotEquals(0, userCreated.getBody().get().getId());
        ObjectMapper objectMapper = new ObjectMapper();
        HttpResponse<List> phones = client.toBlocking().exchange(
                HttpRequest.POST("/findByPhones",
                        new UserPhonesSearchDTO(Lists.newArrayList("12345"))), List.class);
        assertEquals(userCreated.getBody().get(), objectMapper.convertValue(phones.getBody().get().get(0), UserDTO.class));
    }

    @Test
    void testCreateUser_when_creating_new_user_should_return_created_user() {
        HttpResponse<UserDTO> userCreated = client.toBlocking().exchange(
                HttpRequest.POST("/createUser", new UserCreationDTO("john", "doe", "123456")), UserDTO.class);
        assertNotEquals(0, userCreated.getBody().get().getId());
    }

    @Test
    void testCreateUser_when_creating_existent_user_should_return_duplicated_error() {
        HttpResponse<UserDTO> userCreated = client.toBlocking().exchange(
                HttpRequest.POST("/createUser", new UserCreationDTO("john", "doe", "123457")), UserDTO.class);
        assertEquals(1, userCreated.getBody().get().getId());
        assertThrows(HttpClientResponseException.class, () -> {
            HttpResponse<ErrorDTO> errorRetrieved = client.toBlocking().exchange(
                    HttpRequest.POST("/createUser", new UserCreationDTO("johna", "doeha", "123457")), ErrorDTO.class);
            //TODO dont know how to trigger this assertion for now :)
            assertEquals(Errors.DUPLICATED_ENTITY, errorRetrieved.getBody().get().getCode());
        });
    }
}
