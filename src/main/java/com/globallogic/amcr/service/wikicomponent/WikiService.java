package com.globallogic.amcr.service.wikicomponent;


import com.globallogic.amcr.model.wiki.Wiki;

import java.util.List;
import java.util.UUID;

public interface WikiService {

    Wiki save(Wiki wiki);

    Wiki get(UUID id);

    List<Wiki> getAll();


    Wiki update(UUID id, Wiki newWiki);


    void delete(UUID id);
}
