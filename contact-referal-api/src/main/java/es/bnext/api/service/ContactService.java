package es.bnext.api.service;

import com.google.common.collect.Lists;
import es.bnext.api.client.ContactClient;
import es.bnext.api.dto.contact.UserContactsDTO;
import es.bnext.api.dto.contact.UserContactsRequestDTO;
import es.bnext.api.dto.contact.UserContactsResponseDTO;
import es.bnext.api.dto.contact.UserContactsSearchDTO;
import es.bnext.api.dto.user.UserDTO;
import es.bnext.api.dto.user.UserPhonesSearchDTO;
import lombok.RequiredArgsConstructor;

import javax.inject.Singleton;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Singleton
public class ContactService {
    private final ContactClient contactClient;
    private final UserService userService;

    public UserContactsResponseDTO saveContacts(@Valid UserContactsRequestDTO userContactsRequestDTO) {
        List<UserDTO> users = userService.findByPhones(new UserPhonesSearchDTO(Lists.newArrayList(userContactsRequestDTO.getPhone())));
        UserContactsDTO userContactsToService = new UserContactsDTO(users.get(0).getId(), userContactsRequestDTO.getContacts());
        UserContactsDTO updatedContacts = contactClient.createOrUpdateContacts(userContactsToService);
        return new UserContactsResponseDTO(users.get(0), updatedContacts.getContacts());
    }

    public List<UserContactsDTO> findContactsByUserId(@Valid UserPhonesSearchDTO userContactsSearchDTO) {
        List<UserDTO> users = userService.findByPhones(userContactsSearchDTO);
        return contactClient.findByUserIds(new UserContactsSearchDTO(users.stream().map(UserDTO::getId).collect(Collectors.toList())));
    }
}
