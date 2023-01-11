package com.globallogic.amcr.persistence.dao.layoutcomponent;

import com.globallogic.amcr.mapper.layoutcomponent.LayoutMapper;
import com.globallogic.amcr.persistence.model.layoutcomponent.Layout;
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

//    public List<Layout> getLayoutByProfileId(UUID profileId) {
//        return (List<Layout>) layoutMapper.getLayoutByProfileById(profileId);
//    }

    public boolean deleteById(){
        return true;
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
        if (newLayout.getxPosition() == null) {
            newLayout.setxPosition(oldLayout.getxPosition());
        }
        if (newLayout.getyPosition() == null) {
            newLayout.setyPosition(oldLayout.getyPosition());
        }
        if (newLayout.getWidth() == null) {
            newLayout.setWidth(oldLayout.getWidth());
        }
        if (newLayout.getHeight() == null) {
            newLayout.setHeight(oldLayout.getHeight());
        }
        if (newLayout.getIsMovable() == null) {
            newLayout.setIsMovable(oldLayout.getIsMovable());
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
