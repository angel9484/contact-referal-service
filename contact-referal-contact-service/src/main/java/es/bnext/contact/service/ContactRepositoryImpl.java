package es.bnext.contact.service;


import es.bnext.contact.entity.Contact;
import io.micronaut.transaction.annotation.ReadOnly;
import lombok.RequiredArgsConstructor;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Singleton
@RequiredArgsConstructor
public class ContactRepositoryImpl implements ContactRepository {
    private final EntityManager entityManager;

    @Override
    @Transactional
    public List<Contact> saveAll(List<Contact> contacts) {
        List<Contact> contactsToSave = new ArrayList<>();
        for (Contact contact : contacts) {
            Contact contactToSave = contact.toBuilder().build();
            if (contactToSave.getId() > 0) {
                entityManager.remove(contactToSave);
            }
            entityManager.persist(contactToSave);
            contactsToSave.add(contactToSave);
        }
        return contactsToSave;
    }

    @Override
    @Transactional
    public Contact delete(Contact contact) {
        entityManager.remove(contact);
        return contact;
    }

    @Override
    @ReadOnly
    public Map<Integer, List<Contact>> getAllByUserIds(List<Integer> userIds) {
        return Optional.ofNullable(
                entityManager.createQuery("SELECT c FROM Contact c WHERE user_id IN(:userIds)", Contact.class).setParameter("userIds",
                        userIds).getResultList()).orElse(new ArrayList<>()).stream().collect(Collectors.groupingBy(Contact::getUserId));
    }
}
