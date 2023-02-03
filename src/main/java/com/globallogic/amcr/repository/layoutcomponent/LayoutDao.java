package com.globallogic.amcr.repository.layoutcomponent;

import com.globallogic.amcr.model.Layout;
import com.globallogic.amcr.repository.Dao;

import java.util.List;
import java.util.UUID;

public interface LayoutDao extends Dao<Layout, Layout> {

    Layout save(Layout layout, UUID id);


    Layout get(UUID id);

    Layout getPage(String name);

    List<Layout> getAll();


    Layout update(UUID pageId, Layout newLayout, Layout oldLayout);

    void delete(UUID id);
}
