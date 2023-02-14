package com.globallogic.amcr.service.wikicomponent;


import com.globallogic.amcr.model.wiki.Wiki;

import java.util.List;
import java.util.UUID;

public interface WikiService {
    /**
     * saves a wiki object to the database
     *
     * @param wiki takes in an wiki object
     * @return returns the object if the request is successful
     */
    Wiki save(Wiki wiki);
    /**
     * requests a wiki with a given id
     *
     * @param id the id of the wiki to be found
     * @return returns the requested wikis with all fields if found, else throws 404 not found error
     */
    Wiki get(UUID id);

    /**
     * requests all wikis in the database
     *
     * @return returns a list of all saved wikis with all fields
     */
    List<Wiki> getAll();
    /**
     * requests an update to the specified wiki
     *
     * @param id           the id of the wiki to be updated
     * @param newWiki the new wiki object to overwrite the previously saved wiki. the id must match the id of the entry to update
     * @return returns the complete updated wiki
     */

    Wiki update(UUID id, Wiki newWiki);
    /**
     * requests deletion of an entry with a specified id
     *
     * @param id the id of the entry to be deleted
     */

    void delete(UUID id);
}
