package com.globallogic.amcr.persistence.dao;

import java.util.UUID;

public interface CrudDao<T, R> extends Dao<T, R> {

    /**
     * Updates an entry in the database.
     * Ideally the implementation should check whether any fields are left as null in the new entry and those fields should be filled in with data from the old entry to prevent data being overwritten.
     *
     * @param id        The ID of the entry to be updated
     * @param newObject The new object with data to be inserted
     * @param oldObject The old object to be overwritten
     * @return Returns an updated object
     */
    T update(UUID id, T newObject, T oldObject);

    /**
     * Deletes an entry in the database with a specified ID
     *
     * @param id The ID of the entry to be deleted
     * @return Returns the ID of the deleted entry
     */
    UUID delete(UUID id);
}
