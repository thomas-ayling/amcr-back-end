package com.globallogic.amcr.repository.impl.layoutcomponent;

import com.globallogic.amcr.model.Layout;
import com.globallogic.amcr.repository.layoutcomponent.LayoutDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;


@Repository
public class LayoutDaoImpl implements LayoutDao {

    LayoutMapper layoutMapper;

    public LayoutDaoImpl(LayoutMapper layoutMapper) {
        this.layoutMapper = layoutMapper;
    }


    @Transactional
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
        return layoutMapper.get(id);
    }

    public Layout getPage(String name) {
        return layoutMapper.getPage(name);
    }


    public List<Layout> getAll() {
        return layoutMapper.getAll();
    }


    @Override
    public Layout update(UUID id, Layout newLayout, Layout oldLayout) {
        try {
            newLayout.setId(id);
            if (oldLayout.equals(newLayout)) {
                return newLayout;
            }
            if (newLayout.getName() == null) {
                newLayout.setName(oldLayout.getName());
            }
            if (newLayout.getComponents() == null)
                newLayout.setComponents(oldLayout.getComponents());
            if (newLayout.getStatic() == null) {
                newLayout.setStatic(oldLayout.getStatic());
            }
            layoutMapper.update(id, newLayout);
            return newLayout;
        } catch (Exception e) {
            throw new RuntimeException("Layout dao impl", e);
        }
    }


    @Override
    public void delete(UUID id) {
        try {
            layoutMapper.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error in layoutDao - could not delete layout with id " + id, e);
        }
    }


}
