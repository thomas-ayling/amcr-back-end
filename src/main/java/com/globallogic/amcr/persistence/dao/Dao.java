package com.globallogic.amcr.persistence.dao;

import java.util.List;
import java.util.UUID;

/**
 * @param <T> the type of object to be saved
 * @param <R> the type of object to be returned
 */
public interface Dao<T, R> extends AttachmentDao<T, R> {

    /**
     * prepares saves an object to a table
     *
     * @param t    the object to be saved
     * @param uuid the id of the object to be saved
     * @return returns the saved object
     */
    T save(T t, UUID uuid);

    /**
     * requests a single item from the table with specified id
     *
     * @param id the id of the requested entry
     * @return return the requested entry if found, null if not found
     */
    R get(UUID id);

    /**
     * requests all entries in a table
     *
     * @return returns a list of all entries, empty list if there are no entries
     */
    List<R> getAll();
}
