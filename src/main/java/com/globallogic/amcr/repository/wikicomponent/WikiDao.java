package com.globallogic.amcr.repository.wikicomponent;

import com.globallogic.amcr.model.wiki.Wiki;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface WikiDao {

    Wiki get(UUID id);

    List<Wiki> getAll();

    void delete(UUID id);

    Wiki save(Wiki wiki, UUID id);

    Wiki update(UUID id, Wiki newWiki, Wiki oldWiki);

}
