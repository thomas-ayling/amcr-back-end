package com.globallogic.amcr.repository.impl.contacts;

import com.globallogic.amcr.model.contacts.Contacts;
import com.globallogic.amcr.repository.contacts.ContactsDao;
import com.globallogic.amcr.utils.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class ContactsDaoImpl implements ContactsDao {

    private final Logger Log = LoggerFactory.getLogger(ContactsDaoImpl.class.getName());
    private final ContactsMapper contactsMapper;

    public ContactsDaoImpl(ContactsMapper contactsMapper) {
        this.contactsMapper = Assert.assertNotNull(contactsMapper, "Contacts mapper cannot be null");
    }

    @Override
    public Contacts save(Contacts contact, UUID id) {
        contact.setId(id);
        Log.trace("DAO saving new contacts data:\n{}", contact);
        contactsMapper.save(contact);
        return contact;
    }

    @Override
    public Contacts get(UUID id) {
        Log.trace("DAO requesting contacts data with ID {}", id);
        return contactsMapper.get(id);
    }

    @Override
    public List<Contacts> getAll() {
        Log.trace("DAO requesting all contacts data");
        return contactsMapper.getAll();
    }

    @Override
    public List<Contacts> getSpotlitContacts() {
        Log.trace("DAO requesting all spotlit contacts data");
        return contactsMapper.getSpotlitContacts();
    }

    @Override
    public Contacts update(UUID id, Contacts newContact, Contacts oldContact) {
        newContact.setId(id);
        if (oldContact.equals(newContact)) {
            return newContact;
        }
        if (newContact.getImageId() == null) {
            newContact.setImageId(oldContact.getImageId());
        }
        if (newContact.getFullName() == null) {
            newContact.setFullName(oldContact.getFullName());
        }
        if (newContact.getTitle() == null) {
            newContact.setTitle(oldContact.getTitle());
        }
        if (newContact.getDescription() == null) {
            newContact.setDescription(oldContact.getDescription());
        }
        Log.trace("DAO updating contacts data with ID {} and content\n{}\nwith new content:\n{}", id, oldContact, newContact);
        contactsMapper.update(id, newContact);
        return newContact;
    }

    @Override
    public void delete(UUID id) {
        Log.trace("DAO deleting contacts data with ID {}", id);
        contactsMapper.delete(id);
    }
}