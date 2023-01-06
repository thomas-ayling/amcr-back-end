package com.globallogic.amcr.service.pagecontent;

import com.globallogic.amcr.mapper.pagecontent.MarkdownMapper;
import com.globallogic.amcr.persistence.dao.pagecontent.MarkdownDao;
import com.globallogic.amcr.persistence.model.pagecontent.Markdown;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class MarkdownServiceImpl implements MarkdownService {

    private final MarkdownDao markdownDao;

    public MarkdownServiceImpl(MarkdownDao markdownDao) {
        this.markdownDao = markdownDao;
    }

    @Transactional
    public void save(Markdown markdown) {
        try {
            UUID markdownId = UUID.randomUUID();
            markdownDao.save(markdown, markdownId);
        } catch (Exception e) {
            throw new RuntimeException("Error saving in service implementation", e);
        }
    }

    @Transactional(readOnly = true)
    public Markdown get(UUID id) {
        return markdownDao.get(id);
    }

    @Transactional(readOnly = true)
    public List<Markdown> getAll() {
        return markdownDao.getAll();
    }

    @Transactional(readOnly = true)
    public Markdown getLatest() {
        return markdownDao.getLatest();
    }

    @Transactional(readOnly = true)
    public Markdown getByName(String name) {
        return markdownDao.getByName(name);
    }

    @Transactional
    public void update(Markdown markdown, int orderNumber) {
        try {
            markdownDao.update(markdown, orderNumber);
        } catch (Exception e) {
            throw new RuntimeException("Error updating in service implementation", e);
        }
    }

    @Transactional
    public void delete(int orderNumber) {
        try {
            markdownDao.delete(orderNumber);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting in service implementation", e);
        }
    }
}


