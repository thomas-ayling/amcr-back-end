package com.globallogic.amcr.service.sharedcomponents;

import com.globallogic.amcr.model.sharedcomponents.MainCarousel;

import java.util.List;
import java.util.UUID;

public interface MainCarouselService {

    MainCarousel save(MainCarousel mainCarousel);

    MainCarousel get(UUID id);

    MainCarousel getByLocation(String location);

    List<MainCarousel> getAll();

    MainCarousel update(UUID id, MainCarousel mainCarousel);

    void delete(UUID id);

}
