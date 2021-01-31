package es.bnext.api.service;

import com.google.common.collect.Lists;
import es.bnext.api.client.ContactClient;
import es.bnext.api.dto.contact.UserContactsDTO;
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

    public UserContactsDTO saveContacts(UserContactsDTO userContactsDTO) {
        List<UserDTO> users = userService.findByPhones(new UserPhonesSearchDTO(Lists.newArrayList(userContactsDTO.getPhone())));
        userContactsDTO.setUserDTO(users.get(0));
        contactClient.createOrUpdateContacts(userContactsDTO);
        return userContactsDTO;
    }

    public List<UserContactsDTO> findContactsByUserId(@Valid UserPhonesSearchDTO userContactsSearchDTO) {
        List<UserDTO> users = userService.findByPhones(userContactsSearchDTO);
        return contactClient.findByUserIds(new UserContactsSearchDTO(users.stream().map(UserDTO::getId).collect(Collectors.toList())));
    }
}
