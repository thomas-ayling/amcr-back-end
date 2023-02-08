package com.globallogic.amcr.repository.impl.layoutcomponent;

import com.globallogic.amcr.model.Layout;
import com.globallogic.amcr.repository.layoutcomponent.LayoutDao;
import com.globallogic.amcr.utils.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.UUID;


@Repository
public class LayoutDaoImpl implements LayoutDao {

    private final Logger LOG = LoggerFactory.getLogger(LayoutDaoImpl.class);
    private final LayoutMapper layoutMapper;

    public LayoutDaoImpl(LayoutMapper layoutMapper) {
        this.layoutMapper = Assert.assertNotNull(layoutMapper, "Layout mapper can not be null");
    }


    public Layout save(Layout layout, UUID layoutId) {
        try {
            layout.setId(layoutId);

            layoutMapper.save(layout);
            return layout;
        } catch (Exception e) {
            throw new RuntimeException("Error in LayoutDaoImpl - could not save layout", e);
        }
    }


    public Layout get(UUID id) {

        LOG.trace("DAO requesting page with ID {}", id);
        return layoutMapper.get(id);
    }

    public Layout getPage(String name) {
        LOG.trace("DAO requesting page with name {}", name);
        return layoutMapper.getPage(name);
    }


    public List<Layout> getAll() {
        LOG.trace("DAO requesting all pages");
        return layoutMapper.getAll();
    }


    @Override
    public Layout update(UUID id, Layout newLayout, Layout oldLayout) {
        LOG.trace("DAO updating layout with ID {}", id);

        newLayout.setId(id);
        layoutMapper.update(id, newLayout);
        return newLayout;

    }


    @Override
    public void delete(UUID id) {
        LOG.trace("DAO deleting layout with ID {}", id);
        try {
            layoutMapper.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error in layoutDao - could not delete layout with id " + id, e);
        }
    }


}
