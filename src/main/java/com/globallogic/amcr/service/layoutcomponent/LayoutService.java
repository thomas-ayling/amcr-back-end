package com.globallogic.amcr.service.layoutcomponent;


import com.globallogic.amcr.model.Layout;

import java.util.List;
import java.util.UUID;


public interface LayoutService {
    /**
     * Method for saving new layout into the database.
     *
     * @param layout - Layout object containing data for the new layout entry into the database.
     */
    Layout save(Layout layout);

    /**
     * Method that retrieves one layout.
     *
     * @param id - UUID layout id for identifying layout.
     * @return - Layout object containing all the information for selected layout.
     */
    Layout get(UUID id);

    /**
     * Method that retrieves one layout.
     *
     * @param name - String book named for identifying layout.
     * @return layout - Layout Object containing all the information for selected layout.
     */
    Layout getPage(String name);

    /**
     * Method that allows you to all layouts.
     *
     * @return - Layout list containing all the information of the layouts.
     */
    List<Layout> getAll();

    /**
     * Method that allows you to update a layout.
     *
     * @param id            - UUID for identifying which book is being updated.
     * @param newPageLayout - Layout Object containing data that is being updated.
     */
    Layout update(UUID id, Layout newPageLayout);

    /**
     * Method for deleting a Layout from the database.
     *
     * @param id - UUID for identifying which layout to remove.
     */
    void delete(UUID id);


}

