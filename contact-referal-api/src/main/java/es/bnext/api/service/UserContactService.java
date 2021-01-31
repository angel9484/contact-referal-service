package es.bnext.api.service;

import es.bnext.api.dto.contact.ContactDTO;
import es.bnext.api.dto.contact.UserContactsDTO;
import es.bnext.api.dto.user.UserPhonesSearchDTO;
import lombok.RequiredArgsConstructor;

import javax.inject.Singleton;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
@RequiredArgsConstructor
public class UserContactService {
    private final ContactService contactService;

    public List<ContactDTO> getCommonContacts(UserPhonesSearchDTO userPhonesSearchDTO) {
        List<UserContactsDTO> contactsByUserId = contactService.findContactsByUserId(
                new UserPhonesSearchDTO(userPhonesSearchDTO.getPhones()));
        if (contactsByUserId.size() < 2) {
            //throw error
        }
        List<UserContactsDTO> restOfUserContacts = contactsByUserId.subList(1, contactsByUserId.size());
        return contactsByUserId.get(0).getContacts().stream().filter(
                contactDTO -> restOfUserContacts.stream().map(UserContactsDTO::getContacts).allMatch(
                        subContacts -> subContacts.contains(contactDTO))).collect(Collectors.toList());
    }
}
