package es.bnext.contact.service;

import com.google.common.collect.Lists;
import es.bnext.contact.dto.ContactDTO;
import es.bnext.contact.dto.UserContactsDTO;
import es.bnext.contact.dto.UserContactsSearchDTO;
import es.bnext.contact.entity.Contact;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Singleton
public class ContactService {
    private final ContactMapper contactMapper = Mappers.getMapper(ContactMapper.class);
    private final ContactRepository contactRepository;

    public UserContactsDTO saveContacts(UserContactsDTO userContactsDTO) {
        //TODO estaria bien un "check" de "mantener contactos que no vengan
        Map<String, List<Contact>> contactsInDB = contactRepository.getAllByUserIds(
                Lists.newArrayList(userContactsDTO.getUserId())).getOrDefault(userContactsDTO.getUserId(),
                new ArrayList<>()).stream().collect(
                Collectors.groupingBy(Contact::getPhone));
        List<Contact> contactsToUpdateOrCreate = contactMapper.toEntities(userContactsDTO.getContacts()).stream().map(contact -> {
            contact.setUserId(userContactsDTO.getUserId());
            return contact;
        }).collect(Collectors.toList());

        for (Contact contactToUpdateOrCreate : contactsToUpdateOrCreate) {
            Optional.ofNullable(contactsInDB.get(contactToUpdateOrCreate.getPhone())).map(contacts -> {
                int id = contacts.get(0).getId();
                contactToUpdateOrCreate.setId(id);
                contactsInDB.remove(contactToUpdateOrCreate.getPhone());
                return contacts;
            });
        }

        List<Contact> contactsUpdatedOrCreated = contactRepository.saveAll(contactsToUpdateOrCreate);
        contactsInDB.values().stream().flatMap(Collection::stream).forEach(contactRepository::delete);
        List<ContactDTO> contactsUpdatedOrCreatedDTO = contactMapper.toDTOs(contactsUpdatedOrCreated);
        return new UserContactsDTO(userContactsDTO.getUserId(), contactsUpdatedOrCreatedDTO);
    }

    public List<UserContactsDTO> findContactsByUserId(UserContactsSearchDTO userContactsSearchDTO) {
        List<UserContactsDTO> userContactsListToReturn = new ArrayList<>();
        Map<Integer, List<Contact>> contactsByUserIds = contactRepository.getAllByUserIds(userContactsSearchDTO.getUserIds());

        contactsByUserIds.forEach(
                (userId, contacts) -> userContactsListToReturn.add(new UserContactsDTO(userId, contactMapper.toDTOs(contacts))));
        return userContactsListToReturn;
    }
}
