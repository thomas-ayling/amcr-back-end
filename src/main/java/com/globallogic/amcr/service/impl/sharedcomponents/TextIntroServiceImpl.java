package com.globallogic.amcr.service.impl.sharedcomponents;

import com.globallogic.amcr.model.sharedcomponents.TextIntro;
import com.globallogic.amcr.repository.sharedcomponents.TextIntroDao;
import com.globallogic.amcr.service.sharedcomponents.TextIntroService;
import com.globallogic.amcr.utils.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class TextIntroServiceImpl implements TextIntroService {

    private final TextIntroDao textIntroDao;
    private final Logger LOG = LoggerFactory.getLogger(TextIntroServiceImpl.class.getName());

    public TextIntroServiceImpl(TextIntroDao textIntroDao) {
        this.textIntroDao = Assert.assertNotNull(textIntroDao, "Dao cannot be null to request");
    }

    @Transactional(readOnly = true)
    public TextIntro save(TextIntro textIntro) {
        UUID id = UUID.randomUUID();
        LOG.debug("Service saving TextIntro with id {}");
        return textIntroDao.save(textIntro, id);
    }

    @Transactional(readOnly = true)
    public TextIntro get(UUID id) {
        Assert.assertNotNull(id, "Id cannot be null");
        LOG.debug("Service is retrieving TextIntro id {}");
        return textIntroDao.get(id);
    }

    @Transactional(readOnly = true)
    public TextIntro getByLocation(String location) {
        Assert.assertNotNull(location, "Location cannot be null");
        LOG.debug("Retrieving textintro from location {}");
        return textIntroDao.getByLocation(location);
    }

    @Transactional
    public List<TextIntro> getAll() {
        LOG.debug("Service requesting all TextIntros");
        return textIntroDao.getAll();
    }

    @Transactional
    public TextIntro update(UUID id, TextIntro newTextIntro) {
        Assert.assertNotNull(id, "ID cannot be null");
        Assert.assertNotNull(newTextIntro, "New Text intro cannot be null");
        TextIntro oldTextIntro = Assert.assertNotNull(textIntroDao.get(id), "Text intro to be updated cannot be null");
        LOG.debug("Service requesting the update of TextIntro with id {}", id);
        return textIntroDao.update(id, oldTextIntro, newTextIntro);
    }

    @Transactional
    public void delete(UUID id) {
        Assert.assertNotNull(id, "Id cannot be null");
        LOG.debug("Service requesting the deletion of text intro with id {}", id);
        textIntroDao.delete(id);
    }
}
