package com.globallogic.amcr.service.layoutcomponent;

import com.globallogic.amcr.mapper.layoutcomponent.LayoutMapper;
import com.globallogic.amcr.persistence.model.layoutcomponent.Layout;
import com.globallogic.amcr.persistence.dao.layoutcomponent.LayoutDao;
import com.globallogic.amcr.utils.Assert;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class LayoutService {
    public final LayoutMapper layoutMapper;
    private final LayoutDao layoutDao;

    public LayoutService(LayoutMapper layoutMapper, LayoutDao layoutDao) {
        this.layoutMapper = layoutMapper;
        this.layoutDao = layoutDao;
    }

    @Transactional
    public ResponseEntity<Resource> saveLayout(Layout layout) {
        try {
            UUID id = UUID.randomUUID();
            layout.setId(id);
            layoutMapper.save(layout);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional()
    public List<Layout> getAll() {
        return layoutDao.getAll();
    }

    @Transactional()
    public List<Layout> getByPage(String page) {
        return layoutDao.getByPage(page);
    }

    @Transactional()
    public Layout getElementByName(String elementName) {
        return layoutDao.getElementByName(elementName);
    }

    @Transactional()
    public Layout getById(UUID id) {
        return layoutDao.get(id);
    }

    @Transactional
    public void deleteById(UUID id) {
        layoutDao.deleteById(id);
    }

    @Transactional
    public Layout update(UUID id, Layout newLayout) {
        Assert.assertNotNull(id, "ID must be included to update a layout");
        Assert.assertNotNull(newLayout, "New layout must not be null");
        Layout oldLayout = layoutDao.get(id);
        return layoutDao.update(id, newLayout, oldLayout);
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
