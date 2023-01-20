package com.globallogic.amcr.service.layoutcomponent;

import com.globallogic.amcr.mapper.layoutcomponent.LayoutMapper;
import com.globallogic.amcr.persistence.model.layoutcomponent.Layout;
import com.globallogic.amcr.persistence.dao.layoutcomponent.LayoutDao;
import com.globallogic.amcr.utils.Assert;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class LayoutServiceImpl implements LayoutService {
    public final LayoutMapper layoutMapper;
    private final LayoutDao layoutDao;

    public LayoutServiceImpl(LayoutMapper layoutMapper, LayoutDao layoutDao) {
        this.layoutMapper = layoutMapper;
        this.layoutDao = layoutDao;
    }

    @Override
    @Transactional
    public Layout save(Layout layout) {
        Assert.assertNotNull(layout, "Layout cannot be null");
        try {
            UUID layoutId = UUID.randomUUID();
            Layout savedLayout = layoutDao.save(layout, layoutId);
            return savedLayout;
        } catch (Exception e) {
            throw new RuntimeException("Error saving Layout", e);
        }
    }

    @Override
    @Transactional()
    public List<Layout> getAll() {
        return layoutDao.getAll();
    }

    @Override
    @Transactional()
    public List<Layout> getByPage(UUID pageId) {
        return layoutDao.getByPage(page);
    }

    @Override
    @Transactional()
    public Layout getById(UUID id) {
        return layoutDao.get(id);
    }

    @Transactional
    public void deleteById(UUID id) {
        layoutDao.deleteById(id);
    }

    @Override
    @Transactional
    public List<Layout> update(UUID pageId, Layout newPageLayout) {
        Assert.assertNotNull(pageId, "ID must be included to update a layout");
        Assert.assertNotNull(newPageLayout, "New layout must not be null");
        Layout oldPageLayout = layoutDao.get(pageId);
        return layoutDao.update(pageId, newPageLayout, oldPageLayout);
    }

//    @Transactional
//    public List<Layout> updateByPage(String page, Layout newLayout) {
//        Assert.assertNotNull(page, "ID must be included to update a layout");
//        Assert.assertNotNull(newLayout, "New layout must not be null");
//        Layout oldLayout = layoutDao.get(page);
//        return layoutDao.updateByPage(page, newLayout, oldLayout);
//    }
//    @Transactional
//    public Layout updateIsMovable(UUID id, Layout newLayout) {
//        Objects.requireNonNull(id, "ID must be included to update a layout");
//        Objects.requireNonNull(newLayout, "New layout must not be null");
//        Layout oldLayout = layoutDao.get(id);
//        Objects.requireNonNull(oldLayout, "Object with specified ID could not be found. Revise ID and try again");
//        return layoutDao.update(id, newLayout, oldLayout);
//    }


}
