package com.globallogic.amcr.repository.layoutcomponent;

import com.globallogic.amcr.model.Layout;
import com.globallogic.amcr.repository.Dao;


import java.util.List;
import java.util.UUID;


public interface LayoutDao extends Dao<Layout, Layout> {
    /**
     * Method for saving new layout into the database.
     *
     * @param layout - Layout object containing data for the new layout entry into the database.
     * @param id     - UUID book id for identifying layout
     */
    Layout save(Layout layout, UUID id);

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
     * Requests all layouts from the layout table
     *
     * @return Returns a list of Layout
     */
    List<Layout> getAll();

    /**
     * Method that allows you to update a layout.
     *
     * @param pageId    - UUID for identifying which book is being updated.
     * @param newLayout - Layout Object containing data that is updating the old layout .
     * @param oldLayout - Layout Object containing data that is being updated.
     */
    Layout update(UUID pageId, Layout newLayout, Layout oldLayout);

    /**
     * Method for deleting a Layout from the database.
     *
     * @param id - UUID for identifying which layout to remove.
     */
    void delete(UUID id);
}
