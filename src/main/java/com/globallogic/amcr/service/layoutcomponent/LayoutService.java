package com.globallogic.amcr.service.layoutcomponent;


import com.globallogic.amcr.model.Layout;

import java.util.List;
import java.util.UUID;


public interface LayoutService {
    Layout save(Layout layout);

    Layout get(UUID id);

    Layout getPage(String name);

    List<Layout> getAll();


    Layout update(UUID id, Layout newPageLayout);

    void delete(UUID id);


}

