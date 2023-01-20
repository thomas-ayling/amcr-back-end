package com.globallogic.amcr.service.layoutcomponent;


import com.globallogic.amcr.persistence.model.layoutcomponent.Layout;

import java.util.List;
import java.util.UUID;


public interface LayoutService {
    Layout save(Layout layout);


    Layout getById(UUID id);

    List<Layout> getAll();


    List<Layout> getByPage(UUID pageId);

    List<Layout> update(UUID pageId, Layout newPageLayout);

    void deleteById(UUID id);


}

