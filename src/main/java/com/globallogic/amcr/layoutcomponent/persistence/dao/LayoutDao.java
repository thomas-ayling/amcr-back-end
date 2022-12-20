package com.globallogic.amcr.layoutcomponent.persistence.dao;

import com.globallogic.amcr.layoutcomponent.mapper.LayoutMapper;
import com.globallogic.amcr.layoutcomponent.persistence.model.Layout;
import com.globallogic.amcr.persistence.dao.Dao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public class LayoutDao implements Dao<Layout,Layout > {

    LayoutMapper layoutMapper;

    public LayoutDao(LayoutMapper layoutMapper) {
        this.layoutMapper = layoutMapper;
    }

    @Override
    public void save(Layout layout, UUID id) {

    }

    @Override
    public Layout get(UUID id) {
        return null;
    }

    @Override
    public List<Layout> getAll() {
        return layoutMapper.getAll();
    }

    public List<Layout> getLayoutByPage(String page) {
        return (List<Layout>) layoutMapper.getLayoutByPage(page);
    }
}
