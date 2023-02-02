package com.globallogic.amcr.repository.impl.wikicomponent;

import com.globallogic.amcr.model.wikipage.WikiPage;
import com.globallogic.amcr.repository.wikipagecomponent.WikiPageDao;
import com.globallogic.amcr.utils.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class WikiPageDaoImpl implements WikiPageDao {

    private final Logger Log = LoggerFactory.getLogger(WikiPageDaoImpl.class);

    private final WikiPageMapper wikiPageMapper;

    public WikiPageDaoImpl(WikiPageMapper wikiPageMapper){
        this.wikiPageMapper = Assert.assertNotNull(wikiPageMapper, "Wiki page mapper cannot be null");
    }

    @Override
    public WikiPage save(WikiPage wikiPage, UUID wikiPageId) {
        try {
            wikiPage.setId(wikiPageId);
            Log.trace("DAO saving new wiki page: \n{}", wikiPage);
            wikiPageMapper.save(wikiPage);
            return wikiPage;
        } catch (Exception e){
            throw new RuntimeException("Error in WikiPageDaoImpl - could not save wiki page");
        }
    }

    @Override
    public WikiPage get(UUID id){
        Log.trace("DAO requesting wiki page with ID {}", id);
        return wikiPageMapper.get(id);
    }
    @Override
    public List<WikiPage> getAll(){
        Log.trace("DAO requesting all wiki pages");
        return wikiPageMapper.getAll();
    }

    @Override
    public WikiPage update(UUID id, WikiPage newWikiPage, WikiPage oldWikiPage){
        newWikiPage.setId(id);
        if (oldWikiPage.equals(newWikiPage)){
            return newWikiPage;
        }
        if (newWikiPage.getTitle() == null){
            newWikiPage.setTitle(oldWikiPage.getTitle());
        }
        if (newWikiPage.getOverview() == null){
            newWikiPage.setOverview(oldWikiPage.getOverview());
        }
        if (newWikiPage.getSubImage() == null){
            newWikiPage.setSubImage(oldWikiPage.getSubImage());
        }
        if (newWikiPage.getSubTitle() == null){
            newWikiPage.setSubTitle(oldWikiPage.getSubTitle());
        }
        if (newWikiPage.getSubOverview() == null){
            newWikiPage.setSubOverview(oldWikiPage.getSubOverview());
        }
        if (newWikiPage.getBody() == null){
            newWikiPage.setBody(oldWikiPage.getBody());
        }
        Log.trace("DAO updating wiki page with ID {} and content: \n{}\n\n\n\nwith new wiki page: \n\n{}", id, oldWikiPage, newWikiPage);
        wikiPageMapper.update(id, newWikiPage);
        return newWikiPage;
    }

    @Override
    public void delete(UUID id) {
        try {
            Log.trace("DAO deleting wiki page with ID {}", id);
            wikiPageMapper.delete(id);
        } catch (Exception e) {
            throw new RuntimeException("Error in WikiPageDaoImpl - could not delete wiki page with id " + id, e);
        }
    }

}


