package com.globallogic.amcr.repository.sharedcomponents;

import com.globallogic.amcr.model.pagecontent.Diagram;
import com.globallogic.amcr.model.sharedcomponents.TextIntro;
import com.globallogic.amcr.repository.CrudDao;

import java.util.List;

public interface TextIntroDao extends CrudDao<TextIntro, TextIntro> {
    TextIntro getByLocation(String location);
}
