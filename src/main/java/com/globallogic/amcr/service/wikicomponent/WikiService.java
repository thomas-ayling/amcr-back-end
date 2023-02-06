package com.globallogic.amcr.service.wikicomponent;


import com.globallogic.amcr.model.wiki.Wiki;

import java.util.List;
import java.util.UUID;

public interface WikiService {

    /**
     * saves a wiki page object to the database
     *
     * @param wiki takes in a wiki page object
     * @return returns the wiki page object if the request is successful
     */
    Wiki save(Wiki wiki);

    /**
     * requests a wiki page with a given id
     *
     * @param id the id of the wiki page to be found
     * @return returns the requested wiki pages with all fields if found, else throws 404 not found error
     */
    Wiki get(UUID id);

    /**
     * requests all wiki pages in the database
     *
     * @return returns a list of all saved wiki pages with all fields
     */
    List<Wiki> getAll();

    /**
     * requests the overviews of all wiki pages in the database
     *
     * @return returns a list of overviews of all entries in the database
     */

    Wiki update(UUID id, Wiki newWiki);

    /**
     * requests deletion of an entry with a specified id
     *
     * @param id the id of the entry to be deleted
     */
    void delete(UUID id);
}
