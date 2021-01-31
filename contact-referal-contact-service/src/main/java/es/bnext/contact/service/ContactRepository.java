package es.bnext.contact.service;

import es.bnext.contact.entity.Contact;

import java.util.List;
import java.util.Map;

public interface ContactRepository {
    Contact delete(Contact contact);

    List<Contact> saveAll(List<Contact> contact);

    Map<Integer, List<Contact>> getAllByUserIds(List<Integer> userIds);
}
