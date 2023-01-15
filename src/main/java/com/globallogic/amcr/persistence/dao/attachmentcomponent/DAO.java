package com.globallogic.amcr.persistence.dao.attachmentcomponent;

import com.globallogic.amcr.persistence.payload.attachmentcomponent.AttachmentMetadata;

import java.util.List;
import java.util.UUID;

/**
 *
 * @param <T> - to be saved.
 * @param <R> - to be returned.
 */
public interface DAO<T, R> {

    /**
     * Attempts to save an entry to the database
     * @param t - the entry to be saved to the database
     */
    void save(T t);

    /**
     *
     * @param id - the ID of the requested entry.
     * @return - returns the requested entry if it has been found, otherwise null.
     */
    R get(UUID id);

    /**
     * Requests all entries in the database to be returned.
     * @return - returns a list of all entries.
     */
    List<AttachmentMetadata> getALl();
}
