package com.globallogic.amcr.service.impl.layoutcomponent;

import com.globallogic.amcr.repository.layoutcomponent.LayoutDao;
import com.globallogic.amcr.model.Layout;
import com.globallogic.amcr.service.layoutcomponent.LayoutService;
import com.globallogic.amcr.utils.Assert;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class LayoutServiceImpl implements LayoutService {


    private final LayoutDao layoutDao;


    public LayoutServiceImpl(LayoutDao layoutDao) {
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
    @Transactional
    public Layout get(UUID id) {
        return layoutDao.get(id);
    }

    @Override
    @Transactional
    public Layout getPage(String name) {
        return layoutDao.getPage(name);
    }


    @Transactional
    public void delete(UUID id) {
        layoutDao.delete(id);
    }

    @Override
    @Transactional
    public Layout update(UUID id, Layout newPageLayout) {
        try {
            Assert.assertNotNull(id, "ID must be included to update a layout");
            Assert.assertNotNull(newPageLayout, "New layout must not be null");
            Layout oldPageLayout = layoutDao.get(id);
            return layoutDao.update(id, newPageLayout, oldPageLayout);
        } catch (Exception e) {
            throw new RuntimeException("Service layer issue", e);
        }
    }


}
