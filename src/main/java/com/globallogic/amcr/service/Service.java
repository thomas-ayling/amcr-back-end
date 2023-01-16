package com.globallogic.amcr.service;

import java.util.List;
import java.util.UUID;

public interface Service<T> {

    /**
     * Sends an object of type T to the DAO
     *
     * @param t The object to be saved
     * @return Returns a complete copy of the object as saved in the database
     */
    T save(T t);

    /**
     * Requests a database entry with a given ID
     *
     * @param id The ID of the entry to be requested
     * @return Returns the requested entry as an object
     */
    T get(UUID id);

    /**
     * Requests all entries in the database
     *
     * @return Returns a list of all entries as a list of objects
     */
    List<T> getAll();
}
