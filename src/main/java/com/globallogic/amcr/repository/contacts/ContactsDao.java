package com.globallogic.amcr.repository.contacts;

import com.globallogic.amcr.model.contacts.Contacts;
import com.globallogic.amcr.repository.CrudDao;

import java.util.List;

public interface ContactsDao extends CrudDao<Contacts, Contacts> {

    /**
     * Requests all entries in the contacts table where the spotlight field is true
     *
     * @return Returns a list of Contacts objects
     */
    List<Contacts> getSpotlitContacts();
}