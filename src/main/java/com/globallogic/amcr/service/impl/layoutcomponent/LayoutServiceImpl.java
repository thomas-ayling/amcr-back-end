package com.globallogic.amcr.service.impl.layoutcomponent;

import com.globallogic.amcr.repository.layoutcomponent.LayoutDao;
import com.globallogic.amcr.model.Layout;
import com.globallogic.amcr.service.layoutcomponent.LayoutService;
import com.globallogic.amcr.utils.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class LayoutServiceImpl implements LayoutService {

    private final Logger Log = LoggerFactory.getLogger(LayoutServiceImpl.class);
    private final LayoutDao layoutDao;


    public LayoutServiceImpl(LayoutDao layoutDao) {
        this.layoutDao = Assert.assertNotNull(layoutDao, "Layout Dao can't be null");

    }

    @Override
    @Transactional
    public Layout save(Layout layout) {
        Assert.assertNotNull(layout, "Layout cannot be null");
        UUID layoutId = UUID.randomUUID();
        Log.debug("LayoutServiceImpl saving new page");
        Layout savedLayout = layoutDao.save(layout, layoutId);
        return savedLayout;

    }

    @Override
    @Transactional()
    public List<Layout> getAll() {
        Log.trace("LayoutServiceImpl requesting all pages");
        return layoutDao.getAll();
    }


    @Override
    @Transactional
    public Layout get(UUID id) {
        Assert.assertNotNull(id, "ID cannot be null");
        Log.trace("LayoutServiceImpl requesting page with id {}", id);
        return layoutDao.get(id);
    }

    @Override
    @Transactional
    public Layout getPage(String name) {
        Assert.assertNotNull(name, "Name cannot be null");
        Log.trace("LayoutServiceImpl requesting page with name {}", name);
        return layoutDao.getPage(name);
    }


    @Transactional
    public void delete(UUID id) {
        Assert.assertNotNull(id, "ID cannot be null");
        layoutDao.delete(id);
    }

    @Override
    @Transactional
    public Layout update(UUID id, Layout newPageLayout) {
        Assert.assertNotNull(id, "ID must be included to update a layout");
        Assert.assertNotNull(newPageLayout, "New layout must not be null");
        Layout oldPageLayout = layoutDao.get(id);
        Log.trace("LayoutServiceImpl updating page with id {}", id);
        return layoutDao.update(id, newPageLayout, oldPageLayout);

    }


}
