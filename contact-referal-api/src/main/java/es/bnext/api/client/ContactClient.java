package es.bnext.api.client;

import es.bnext.api.dto.contact.UserContactsDTO;
import es.bnext.api.dto.contact.UserContactsSearchDTO;
import es.bnext.api.error.ErrorDTO;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;

import java.util.List;

@Client(value = "${client.contact.url}", errorType = ErrorDTO.class)
public interface ContactClient {
    @Post("/contacts/createOrUpdateContacts")
    UserContactsDTO createOrUpdateContacts(@Body UserContactsDTO userContactsDTO);

    @Post("/contacts/findByUserIds")
    List<UserContactsDTO> findByUserIds(@Body UserContactsSearchDTO userContactsSearchDTO);
}
