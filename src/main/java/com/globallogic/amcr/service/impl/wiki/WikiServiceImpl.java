package com.globallogic.amcr.service.impl.wiki;

import com.globallogic.amcr.model.wiki.Wiki;
import com.globallogic.amcr.repository.wikicomponent.WikiDao;
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
    private final Logger log = LoggerFactory.getLogger(WikiServiceImpl.class);

    public WikiServiceImpl(WikiDao wikiDao) {
        this.wikiDao = wikiDao;
    }


//    public WikiServiceImpl(WikiDao wikiDao) {
//        this.wikiDao = Assert.assertNotNull(wikiDao, "WikiDao is not present");
//    }

    @Override
    @Transactional
    public Wiki save(Wiki wiki) {
        Assert.assertNotNull(wiki, "Wiki Page cannot be null");
        try {
            UUID wikiId = UUID.randomUUID();
            log.debug("Service saving new wiki page");
            return wikiDao.save(wiki, wikiId);
        } catch (Exception e) {
            throw new RuntimeException("Error in WikiService - could not save wiki page", e);
        }
    }


    @Override
    @Transactional
    public Wiki get(UUID id) {
        Assert.assertNotNull(id, "ID cannot be null to get wiki entry");
        log.debug("Service requesting wiki with ID {}", id);
        return wikiDao.get(id);
    }

    @Override
    @Transactional
    public List<Wiki> getAll() {
        log.debug("Requesting all wiki pages");
        return wikiDao.getAll();
    }

    @Override
    @Transactional
    public Wiki update(UUID id, Wiki newWiki) {
        Assert.assertNotNull(id, "ID must be included to update a wiki page");
        Assert.assertNotNull(newWiki, "New Wiki Page must not be null");
        Wiki oldWiki = Assert.assertNotNull(wikiDao.get(id), "Object with specified ID could not be found. Revise ID and try again");
        log.debug("Service updating wiki with ID {}", id);
        return wikiDao.update(id, newWiki, oldWiki);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        Assert.assertNotNull(id, "ID cannot be null to have entry deleted");
        log.debug("Service requesting deletion of wiki page with ID {}", id);
        wikiDao.delete(id);
    }
}
