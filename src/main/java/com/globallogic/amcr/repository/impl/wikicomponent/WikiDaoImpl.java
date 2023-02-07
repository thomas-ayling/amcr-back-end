package com.globallogic.amcr.repository.impl.wikicomponent;

import com.globallogic.amcr.model.wiki.Wiki;
import com.globallogic.amcr.repository.wikicomponent.WikiDao;
import com.globallogic.amcr.utils.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class WikiDaoImpl implements WikiDao {

    private final Logger Log = LoggerFactory.getLogger(WikiDaoImpl.class);

    private final WikiMapper wikiMapper;

    public WikiDaoImpl(WikiMapper wikiMapper){
        this.wikiMapper = Assert.assertNotNull(wikiMapper, "Wiki page mapper cannot be null");
    }

    @Override
    public Wiki save(Wiki wiki, UUID wikiId) {
        try {
            wiki.setId(wikiId);
            Log.trace("DAO saving new wiki page: \n{}", wiki);
            wikiMapper.save(wiki);
            return wiki;
        } catch (Exception e){
            throw new RuntimeException("Error in WikiDaoImpl - could not save wiki page", e);
        }
    }

    @Override
    public Wiki get(UUID id){
        Log.trace("DAO requesting wiki page with ID {}", id);
        return wikiMapper.get(id);
    }
    @Override
    public List<Wiki> getAll(){
        Log.trace("DAO requesting all wiki pages");
        return wikiMapper.getAll();
    }

    @Override
    public Wiki update(UUID id, Wiki newWiki, Wiki oldWiki){
        newWiki.setId(id);
        if (oldWiki.equals(newWiki)){
            return newWiki;
        }
        if (newWiki.getTitle() == null){
            newWiki.setTitle(oldWiki.getTitle());
        }
        if (newWiki.getOverview() == null){
            newWiki.setOverview(oldWiki.getOverview());
        }
        if (newWiki.getSubImage() == null){
            newWiki.setSubImage(oldWiki.getSubImage());
        }
        if (newWiki.getSubTitle() == null){
            newWiki.setSubTitle(oldWiki.getSubTitle());
        }
        if (newWiki.getSubOverview() == null){
            newWiki.setSubOverview(oldWiki.getSubOverview());
        }
        if (newWiki.getDiagram() == null){
            newWiki.setDiagram(oldWiki.getDiagram());
        }
        Log.trace("DAO updating wiki page with ID {} and content: \n{}\n\n\n\nwith new wiki page: \n\n{}", id, oldWiki, newWiki);
        wikiMapper.update(id, newWiki);
        return newWiki;
    }

    @Override
    public void delete(UUID id) {
        try {
            Log.trace("DAO deleting wiki page with ID {}", id);
            wikiMapper.delete(id);
        } catch (Exception e) {
            throw new RuntimeException("Error in WikiDaoImpl - could not delete wiki page with id " + id, e);
        }
    }

}


