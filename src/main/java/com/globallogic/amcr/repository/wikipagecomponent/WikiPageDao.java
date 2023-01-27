package com.globallogic.amcr.repository.wikipagecomponent;

import com.globallogic.amcr.model.wikipage.WikiPage;
import com.globallogic.amcr.model.wikipage.WikiPageOverview;
import com.globallogic.amcr.repository.CrudDao;

import java.util.List;

public interface WikiPageDao extends CrudDao<WikiPage, WikiPage> {

    /*
    * Requests an overview of all the entries in the wiki_page table
    *
     * @return Returns a list of WikiPageOverviews
    * */

    List<WikiPageOverview> getAllOverviews();
}
