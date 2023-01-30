package com.globallogic.amcr.repository.wikipagecomponent;

import com.globallogic.amcr.model.wikipage.WikiPage;
import com.globallogic.amcr.repository.CrudDao;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface WikiPageDao extends CrudDao<WikiPage, WikiPage> {

}
