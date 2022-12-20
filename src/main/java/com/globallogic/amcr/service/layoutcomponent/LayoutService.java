package com.globallogic.amcr.service.layoutcomponent;

import com.globallogic.amcr.mapper.layoutcomponent.LayoutMapper;
import com.globallogic.amcr.persistence.model.layoutcomponent.Layout;
import com.globallogic.amcr.persistence.dao.layoutcomponent.LayoutDao;
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
    public List<Layout> getLayoutByPage(String page) {
        return layoutDao.getLayoutByPage(page);
    }

    @Transactional()
    public List<Layout> getAll() {
        return layoutDao.getAll();
    }



}
