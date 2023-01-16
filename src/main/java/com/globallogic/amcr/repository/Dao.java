package com.globallogic.amcr.repository;

import java.util.List;
import java.util.UUID;

/**
 * @param <T> The type of object to be saved
 * @param <R> The type of object to be returned
 */
public interface Dao<T, R> {

    /**
     * Prepares and saves an object to a table
     *
     * @param t    The object to be saved
     * @param uuid The id of the object to be saved
     * @return Returns the saved object
     */
    T save(T t, UUID uuid);

    /**
     * Requests a single item from the table with specified id
     *
     * @param id The id of the requested entry
     * @return Return the requested entry if found, null if not found
     */
    R get(UUID id);

    /**
     * Requests all entries in a table
     *
     * @return Returns a list of all entries, empty list if there are no entries
     */
    List<R> getAll();
}
