package es.bnext.api.service;

import es.bnext.api.entity.Contact;
import es.bnext.contact.dto.ContactDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface ContactMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userId", ignore = true)
    Contact toEntity(ContactDTO userContactsDTO);

    ContactDTO toDTO(Contact contact);

    List<ContactDTO> toDTOs(List<Contact> contacts);

    List<Contact> toEntities(List<ContactDTO> contacts);
}
