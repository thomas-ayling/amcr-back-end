package com.globallogic.amcr.service.impl.sharedcomponents;

import com.globallogic.amcr.model.sharedcomponents.MainCarousel;
import com.globallogic.amcr.repository.sharedcomponents.MainCarouselDao;
import com.globallogic.amcr.service.sharedcomponents.MainCarouselService;
import com.globallogic.amcr.utils.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class MainCarouselServiceImpl implements MainCarouselService {

    private final MainCarouselDao mainCarouselDao;
    private final Logger LOG = LoggerFactory.getLogger(MainCarouselServiceImpl.class.getName());

    public MainCarouselServiceImpl(MainCarouselDao mainCarouselDao) {
        this.mainCarouselDao = mainCarouselDao;
    }

    @Override
    @Transactional
    public MainCarousel save(MainCarousel mainCarousel) {
        try {
            UUID mainCarouselId = UUID.randomUUID();
            LOG.debug("Service saving new Main Carousel");
            return mainCarouselDao.save(mainCarousel, mainCarouselId);
        } catch (Exception e) {
            throw new RuntimeException("Error in MainCarouselService - could not save", e);
        }
    }

    @Override
    public MainCarousel get(UUID id) {
        Assert.assertNotNull(id, "ID cannot be null to request entry");
        LOG.debug("Service requesting main carousel with ID {}", id);
        return mainCarouselDao.get(id);
    }

    @Override
    public MainCarousel getByLocation(String location) {
        Assert.assertNotNull(location, "location cannot be null to request entry");
        LOG.debug("Service requesting main carousel with location {}", location);
        return mainCarouselDao.getByLocation(location);
    }

    @Override
    public List<MainCarousel> getAll() {
        LOG.debug("Service requesting all main carousels");
        return mainCarouselDao.getAll();
    }

    @Override
    public MainCarousel update(UUID id, MainCarousel mainCarousel) {
        Assert.assertNotNull(id, "ID must be included to update a main carousel");
        Assert.assertNotNull(mainCarousel, "New main carousel must not be null");
        MainCarousel oldMainCarousel = Assert.assertNotNull(mainCarouselDao.get(id), "Object with specified ID could not be found. Revise ID and try again");
        LOG.debug("Service updating main carousel with ID {}", id);
        return mainCarouselDao.update(id, mainCarousel, oldMainCarousel);
    }

    @Override
    public void delete(UUID id) {
        Assert.assertNotNull(id, "id cannot be null to request entry");
        LOG.debug("Service requesting main carousel with id {}", id);
        mainCarouselDao.delete(id);
    }
}
