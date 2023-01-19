package com.globallogic.amcr.persistence.dao.layoutcomponent;

import com.globallogic.amcr.mapper.layoutcomponent.LayoutMapper;
import com.globallogic.amcr.persistence.model.layoutcomponent.Layout;
import com.globallogic.amcr.persistence.dao.Dao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public class LayoutDao implements Dao<Layout, Layout> {

    LayoutMapper layoutMapper;

    public LayoutDao(LayoutMapper layoutMapper) {
        this.layoutMapper = layoutMapper;
    }

    @Override
    public void save(Layout layout, UUID id) {

    }

    @Override
    public Layout get(UUID id) {
        return layoutMapper.getById(id);
    }

    @Override
    public List<Layout> getAll() {
        return layoutMapper.getAll();
    }

    public List<Layout> getByPage(String page) {
        return layoutMapper.getByPage(page);
    }

//    public List<Layout> getLayoutByProfileId(UUID profileId) {
//        return (List<Layout>) layoutMapper.getLayoutByProfileById(profileId);
//    }


    public void deleteById(UUID id) {
        try {
            layoutMapper.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error in layoutDao - could not delete layout with id " + id, e);
        }
    }

    public Layout getElementByName(String elementName) {
        return (Layout) layoutMapper.getByElementName(elementName);
    }


    public Layout update(UUID id, Layout newLayout, Layout oldLayout) {
        newLayout.setId(id);
        if (oldLayout.equals(newLayout)) {
            return newLayout;
        }
        if (newLayout.getElementName() == null) {
            newLayout.setElementName(oldLayout.getElementName());
        }

        if (newLayout.getStatic() == null) {
            newLayout.setStatic(oldLayout.getStatic());
        }
        if (newLayout.getPage() == null) {
            newLayout.setPage(oldLayout.getPage());
        }
        layoutMapper.update(id, newLayout);
        return newLayout;
    }

    public List<Layout> updateByPage(UUID id, List<Layout> newLayouts, List<Layout> oldLayouts) {
        newLayouts.setId(id);
        if (oldLayouts.equals(newLayouts)) {
            return newLayouts;
        }
        if (newLayouts.getElementName() == null) {
            newLayouts.setElementName(oldLayouts.getElementName());
        }

        if (newLayouts.getStatic() == null) {
            newLayouts.setStatic(oldLayouts.getStatic());
        }
        if (newLayouts.getPage() == null) {
            newLayouts.setPage(oldLayouts.getPage());
        }
        layoutMapper.update(id, newLayouts);
        return newLayouts;
    }

    /*layoutMapper.updateIsMovable(page, newLayout);
        newLayout.setIsMovable(isMovable);
        return newLayout;
}*/
}
