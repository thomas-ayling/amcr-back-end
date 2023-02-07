package com.globallogic.amcr.service.impl.wiki;

import com.globallogic.amcr.repository.wikicomponent.WikiDao;
import com.globallogic.amcr.model.wiki.Wiki;
import com.globallogic.amcr.service.wikicomponent.WikiService;
import com.globallogic.amcr.utils.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class WikiServiceImpl implements WikiService {

    private final WikiDao wikiDao;
    private final Logger Log = LoggerFactory.getLogger(WikiServiceImpl.class);



    public WikiServiceImpl(WikiDao wikiDao) {
        this.wikiDao = Assert.assertNotNull(wikiDao, "WikiDao is not present");
    }

    @Override
    @Transactional
    public Wiki save(Wiki wiki) {
        Assert.assertNotNull(wiki, "Wiki Page cannot be null");
        try {
            UUID wikiId = UUID.randomUUID();
            Log.debug("Service saving new wiki page");
            return wikiDao.save(wiki, wikiId);
        } catch (Exception e) {
            throw new RuntimeException("Error in WikiService - could not save wiki page", e);
        }
    }


    @Override
    public Wiki get(UUID id) {
        Assert.assertNotNull(id, "ID cannot be null to get wiki entry");
        Log.debug("Service requesting wiki with ID {}", id);
        return wikiDao.get(id);
    }

    @Override
    public List<Wiki> getAll() {
        Log.debug("Requesting all wiki pages");
        return wikiDao.getAll();
    }

    @Override
    public Wiki update(UUID id, Wiki newWiki) {
        Assert.assertNotNull(id, "ID must be included to update a wiki page");
        Assert.assertNotNull(newWiki, "New Wiki Page must not be null");
        Wiki oldWiki = Assert.assertNotNull(wikiDao.get(id), "Object with specified ID could not be found. Revise ID and try again");
        Log.debug("Service updating wiki with ID {}", id);
        return wikiDao.update(id, newWiki, oldWiki);
    }

    @Override
    public void delete(UUID id) {
        Assert.assertNotNull(id, "ID cannot be null to have entry deleted");
        Log.debug("Service requesting deletion of wiki page with ID {}", id);
        wikiDao.delete(id);
    }
}
