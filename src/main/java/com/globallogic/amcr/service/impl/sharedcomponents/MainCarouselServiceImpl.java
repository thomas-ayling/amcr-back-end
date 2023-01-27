package com.globallogic.amcr.service.impl.sharedcomponents;

import com.globallogic.amcr.model.sharedcomponents.MainCarousel;
import com.globallogic.amcr.service.sharedcomponents.MainCarouselService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class MainCarouselServiceImpl implements MainCarouselService {
    @Override
    @Transactional
    public MainCarousel save(MainCarousel mainCarousel) {
    return null;}

    @Override
    public MainCarousel get(UUID id) {
        return null;
    }

    @Override
    public MainCarousel getByLocation(String location) {
        return null;
    }

    @Override
    public List<MainCarousel> getAll() {
        return null;
    }

    @Override
    public MainCarousel update(UUID id, MainCarousel mainCarousel) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }
}
