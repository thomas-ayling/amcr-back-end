package com.globallogic.amcr.repository.wikicomponent;

import com.globallogic.amcr.model.wiki.Wiki;
import com.globallogic.amcr.repository.CrudDao;
import org.springframework.stereotype.Repository;

@Repository
public interface WikiDao extends CrudDao<Wiki, Wiki> {

}
