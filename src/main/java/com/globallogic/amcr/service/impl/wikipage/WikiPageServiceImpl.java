package com.globallogic.amcr.service.impl.wikipage;

import com.globallogic.amcr.repository.wikipagecomponent.WikiPageDao;
import com.globallogic.amcr.model.wikipage.WikiPage;
import com.globallogic.amcr.service.wikipagecomponent.WikiPageService;
import com.globallogic.amcr.utils.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class WikiPageServiceImpl implements WikiPageService {

    private final Logger Log = LoggerFactory.getLogger(WikiPageServiceImpl.class);

    private final WikiPageDao wikiPageDao;

    public WikiPageServiceImpl(WikiPageDao wikiPageDao) {
        this.wikiPageDao = Assert.assertNotNull(wikiPageDao, "WikiPageDao is not present");
    }

    @Override
    @Transactional
    public WikiPage save(WikiPage wikiPage) {
        Assert.assertNotNull(wikiPage, "Wiki Page cannot be null");
        try {
            UUID wikiPageId = UUID.randomUUID();
            Log.debug("Service saving new wiki page");
            return wikiPageDao.save(wikiPage, wikiPageId);
        } catch (Exception e) {
            throw new RuntimeException("Error in WikiPageService - could not save wiki page", e);
        }
    }


    @Override
    @Transactional
    public WikiPage get(UUID id) {
        Assert.assertNotNull(id, "ID cannot be null to get wiki entry");
        return wikiPageDao.get(id);
    }

    @Override
    @Transactional
    public List<WikiPage> getAll() {
        Log.debug("Requesting all wiki pages");
        return wikiPageDao.getAll();
    }

    @Override
    @Transactional
    public WikiPage update(UUID id, WikiPage newWikiPage) {
        Assert.assertNotNull(id, "ID must be included to update a wiki page");
        Assert.assertNotNull(newWikiPage, "New Wiki Page must not be null");
        WikiPage oldWikiPage = Assert.assertNotNull(wikiPageDao.get(id), "Object with specified ID could not be found. Revise ID and try again");
        return wikiPageDao.update(id, newWikiPage, oldWikiPage);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        Assert.assertNotNull(id, "ID cannot be null to have entry deleted");
        Log.debug("Service requesting deletion of wiki page with ID {}", id);
        wikiPageDao.delete(id);
    }
}
