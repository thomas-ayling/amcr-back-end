package com.globallogic.amcr.service.impl.contacts;

import com.globallogic.amcr.model.contacts.Contacts;
import com.globallogic.amcr.repository.contacts.ContactsDao;
import com.globallogic.amcr.service.contacts.ContactsService;
import com.globallogic.amcr.utils.Assert;
import com.globallogic.amcr.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ContactsServiceImpl implements ContactsService {

    private final Logger Log = LoggerFactory.getLogger(ContactsServiceImpl.class.getName());
    private final ContactsDao contactsDao;

    public ContactsServiceImpl(ContactsDao contactsDao) {
        this.contactsDao = Assert.assertNotNull(contactsDao, "ContactsDao is not present");
    }

    @Override
    @Transactional
    public Contacts save(Contacts contact) {
        Assert.assertNotNull(contact, "Service: Contacts page data cannot be null");
        UUID id = UUID.randomUUID();
        Log.debug("Service: Saving new contacts page data");
        return contactsDao.save(contact, id);
    }

    @Override
    @Transactional(readOnly = true)
    public Contacts get(UUID id) {
        Assert.assertNotNull(id, "Service: ID cannot be null");
        Log.debug("Service requesting contacts page data with ID {}", id);
        return contactsDao.get(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Contacts> getAll() {
        Log.debug("Service requesting all contacts page data");
        return contactsDao.getAll();
    }

    @Override
    @Transactional
    public List<Contacts> getSpotlitContacts() {
        Log.debug("Service requesting all spotlit contacts page data");
        return contactsDao.getSpotlitContacts();
    }

    @Override
    @Transactional
    public Contacts update(UUID id, Contacts newContact) {
        Assert.assertNotNull(id, "Service: ID must be included to update the contacts page object");
        Assert.assertNotNull(newContact, "Service: New contact page data must be included to update");
        Contacts oldContact = Assert.assertNotNull(contactsDao.get(id), "Object with specified ID could not be found");
        Log.debug("Service updating contacts page data with ID {}", id);
        return contactsDao.update(id, newContact, oldContact);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        Assert.assertNotNull(id, "ID cannot be null to delete entry");
        Log.debug("Service requesting deletion of contacts page data with ID {}", id);
        contactsDao.delete(id);
    }
}
