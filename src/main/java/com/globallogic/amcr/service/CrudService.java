package com.globallogic.amcr.service;

import com.globallogic.amcr.model.casestudies.CaseStudy;

import java.util.UUID;

public interface CrudService<T> extends Service<T> {
    /**
     * Requests the update of a specified entry
     *
     * @param id        The ID of the object to be updated
     * @param newObject The object to overwrite the existing entry.
     * @return Returns the updated object as saved in the database
     */
    CaseStudy update(UUID id, T newObject);

    /**
     * Requests deletion of an entry with a specified ID
     *
     * @param id The ID of the entry to be deleted
     */
    void delete(UUID id);
}
