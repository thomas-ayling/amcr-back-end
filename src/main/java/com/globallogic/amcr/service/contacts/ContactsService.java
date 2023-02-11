package com.globallogic.amcr.service.contacts;

import com.globallogic.amcr.model.contacts.Contacts;

import java.util.List;
import java.util.UUID;

public interface ContactsService {

    /**
     * Saves a contacts page object to the database
     *
     * @param contact the contacts page object with the data to be saved
     * @return returns the contacts page object if the request is successful
     */
    Contacts save(Contacts contact);

    /**
     * Requests contacts page data with the specified ID
     *
     * @param id the ID of the contacts page object that will be returned
     * @return returns the contacts page object with the specified ID
     */
    Contacts get(UUID id);

    /**
     * Requests all contacts page data in the database
     *
     * @return returns a list of all contacts page objects in the database
     */
    List<Contacts> getAll();

    /**
     * Requests all contacts page data in the database where the spotlight parameter is true
     *
     * @return returns a list of all spotlit contacts page objects in the database
     */
    List<Contacts> getSpotlitContacts();

    /**
     * Requests an update to the contacts page object with the specified ID
     *
     * @param id      the ID of the contacts page object to be updated
     * @param contact the contacts page object with the data to be updated
     * @return returns the updated contacts page object
     */
    Contacts update(UUID id, Contacts contact);

    /**
     * Requests the deletion of a contacts page object with the specified ID
     *
     * @param id the ID of the contacts page object to be deleted
     */
    void delete(UUID id);
}
