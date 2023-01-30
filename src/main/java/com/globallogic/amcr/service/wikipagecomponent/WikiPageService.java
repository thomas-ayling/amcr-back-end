package com.globallogic.amcr.service.wikipagecomponent;


import com.globallogic.amcr.model.wikipage.WikiPage;
import com.globallogic.amcr.model.wikipage.WikiPageOverview;

import java.util.List;
import java.util.UUID;

public interface WikiPageService {

    /**
     * saves a wiki page object to the database
     *
     * @param wikiPage takes in a wiki page object
     * @return returns the wiki page object if the request is successful
     */
    WikiPage save(WikiPage wikiPage);

    /**
     * requests a wiki page with a given id
     *
     * @param id the id of the wiki page to be found
     * @return returns the requested wiki pages with all fields if found, else throws 404 not found error
     */
    WikiPage get(UUID id);

    /**
     * requests all wiki pages in the database
     *
     * @return returns a list of all saved wiki pages with all fields
     */
    List<WikiPage> getAll();

    /**
     * requests the overviews of all wiki pages in the database
     *
     * @return returns a list of overviews of all entries in the database
     */

    WikiPage update(UUID id, WikiPage newWikiPage);

    /**
     * requests deletion of an entry with a specified id
     *
     * @param id the id of the entry to be deleted
     */
    void delete(UUID id);
}
