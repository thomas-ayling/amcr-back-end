package com.globallogic.amcr.repository.impl.sharedcomponents;

import com.globallogic.amcr.model.sharedcomponents.MainCarousel;
import com.globallogic.amcr.repository.sharedcomponents.MainCarouselDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class MainCarouselDaoImpl implements MainCarouselDao {
    private final Logger LOG = LoggerFactory.getLogger(MainCarouselDaoImpl.class);
    private final MainCarouselMapper mainCarouselMapper;

    public MainCarouselDaoImpl(MainCarouselMapper mainCarouselMapper) {
        this.mainCarouselMapper = mainCarouselMapper;
    }

    @Override
    public MainCarousel update(UUID id, MainCarousel newObject, MainCarousel oldObject) {
        newObject.setId(id);
        if (oldObject.equals(newObject)){
            return newObject;
        }
        if (newObject.getTitles() == null) {
            newObject.setTitles(oldObject.getTitles());
        }
        if (newObject.getLocation() == null) {
            newObject.setLocation(oldObject.getLocation());
        }
        if (newObject.getDescriptions() == null) {
            newObject.setDescriptions(oldObject.getDescriptions());
        }
        if (newObject.getImageIds() == null) {
            newObject.setImageIds((oldObject.getImageIds()));
        }
        LOG.trace("DAO updating main carousel with Id {}", id);
        mainCarouselMapper.update(id, newObject);
        return newObject;

    }

    @Override
    public void delete(UUID id) {
        LOG.trace("Dao requesting the deletion of main carousel with id {}", id);
        mainCarouselMapper.delete(id);
    }

    @Override
    public MainCarousel save(MainCarousel mainCarousel, UUID id) {
        try {
            mainCarousel.setId(id);
            LOG.trace("DAO saving new main carousel:\n{}", mainCarousel);
            mainCarouselMapper.save(mainCarousel);
            return mainCarousel;
        } catch (Exception e) {
            throw new RuntimeException("Error in mainCarouselDaoImpl - could not save new main carousel", e);
        }
    }

    @Override
    public MainCarousel get(UUID id) {
        LOG.trace("Dao requesting a MainCarousel with id {}", id);
        return mainCarouselMapper.get(id);
    }

    @Override
    public List<MainCarousel> getAll() {
        LOG.trace("Dao requesting all Main Carousels");
        return mainCarouselMapper.getAll();
    }

    @Override
    public MainCarousel getByLocation(String location) {
        LOG.trace("Dao requesting all main carousels with location {}", location);
        return mainCarouselMapper.getByLocation(location);
    }
}
