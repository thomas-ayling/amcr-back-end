package com.globallogic.amcr.repository.impl.sharedcomponents;

import com.globallogic.amcr.model.sharedcomponents.TextIntro;
import com.globallogic.amcr.repository.impl.pagecontent.DiagramDaoImpl;
import com.globallogic.amcr.repository.impl.pagecontent.DiagramMapper;
import com.globallogic.amcr.repository.sharedcomponents.TextIntroDao;
import com.globallogic.amcr.utils.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class TextIntroDaoImpl implements TextIntroDao {

    private final Logger LOG = LoggerFactory.getLogger(DiagramDaoImpl.class.getName());
    private final TextIntroMapper textIntroMapper;

    public TextIntroDaoImpl(TextIntroMapper textIntroMapper) {
        this.textIntroMapper = Assert.assertNotNull(textIntroMapper, "TextIntroMapper not null");
    }

    @Override
    public TextIntro update(UUID id, TextIntro newTextIntro, TextIntro oldTextIntro) {
        newTextIntro.setId(id);
        if(oldTextIntro.equals(newTextIntro)) {
            return newTextIntro;
        }
        if (newTextIntro.getTitle() == null) {
            newTextIntro.setTitle(oldTextIntro.getTitle());
        }
        if (newTextIntro.getDescription() == null) {
            newTextIntro.setDescription(oldTextIntro.getDescription());
        }
        LOG.trace("DAO updating Text intro");
        textIntroMapper.update(id, newTextIntro);
        return newTextIntro;
    }

    @Override
    public void delete(UUID id) {
        try {
            LOG.trace("DAO deleting text intro with Id {}");
            textIntroMapper.delete(id);
        } catch (Exception e) {
            throw new RuntimeException("Error in textIntroDao - could not delete text Intro with id {}"+ id, e);
        }
    }

    @Override
    public TextIntro save(TextIntro textIntro, UUID id) {
        try {
            textIntro.setId(id);
            textIntroMapper.save(textIntro);
            return textIntro;
        } catch (Exception e) {
            throw new RuntimeException("DAO could not save new text intro", e);
        }
    }

    @Override
    public TextIntro get(UUID id) {
        LOG.trace("DAO requesting textIntro with ID {}", id);
        return textIntroMapper.get(id);
    }

    @Override
    public TextIntro getByLocation(String location) {
        LOG.trace("DAO requesting TextIntro's with location {}", location);
        return textIntroMapper.getByLocation(location);
    }

    @Override
    public List<TextIntro> getAll() {
        LOG.trace("DAO requesting all TextIntros");
        return textIntroMapper.getAll();
    }
}
