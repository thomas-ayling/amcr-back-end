package com.globallogic.amcr.repository.sharedcomponents;

import com.globallogic.amcr.model.sharedcomponents.MainCarousel;
import com.globallogic.amcr.repository.CrudDao;

public interface MainCarouselDao extends CrudDao<MainCarousel, MainCarousel> {

    MainCarousel getByLocation(String location);
}
