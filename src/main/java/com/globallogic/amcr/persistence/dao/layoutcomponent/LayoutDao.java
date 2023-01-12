package com.globallogic.amcr.persistence.dao.layoutcomponent;

import com.globallogic.amcr.controller.layoutcomponent.LayoutController;
import com.globallogic.amcr.mapper.layoutcomponent.LayoutMapper;
import com.globallogic.amcr.persistence.model.layoutcomponent.Layout;
import com.globallogic.amcr.persistence.dao.Dao;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    public Layout getElementByName(String elementName){
        return (Layout) layoutMapper.getByElementName(elementName);}

    public List<Layout> getByPage(String page){
        return (List<Layout>) layoutMapper.getByPage(page);}

    public Layout update(UUID id, Layout newLayout, Layout oldLayout) {
        if (oldLayout.equals(newLayout)) {
            newLayout.setId(id);
            return newLayout;
        }
        if (newLayout.getElementName() == null) {
            newLayout.setElementName(oldLayout.getElementName());
        }
        if (newLayout.isMovable() == null) {
            newLayout.setMovable(oldLayout.isMovable());
        }
        if (newLayout.getPage() == null) {
            newLayout.setPage(oldLayout.getPage());
        }
        layoutMapper.update(id, newLayout);
        newLayout.setId(id);
        return newLayout;
    }

    /*layoutMapper.updateIsMovable(page, newLayout);
        newLayout.setIsMovable(isMovable);
        return newLayout;
}*/
}
