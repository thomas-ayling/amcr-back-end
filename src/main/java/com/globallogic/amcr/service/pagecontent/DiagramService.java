package com.globallogic.amcr.service.pagecontent;

import com.globallogic.amcr.persistence.model.pagecontent.Diagram;

import java.util.List;
import java.util.UUID;

public interface DiagramService {

    /**
     * Saves a diagram object to the database
     *
     * @param diagram the diagram object with the data to be saved
     * @return returns the diagram object if the request is successful
     */
    Diagram save(Diagram diagram);

    /**
     *
     * Requests diagram data with the specified ID
     * @param id the id of the diagram object that will be returned
     * @return returns the diagram object with the specified id
     */
    Diagram get (UUID id);

    /**
     * Requests diagram data with the specified node position
     *
     * @param nodePosition the node position of the diagram object that will be returned
     * @return returns the diagram object with the specified node position
     */
    Diagram getByNode (int nodePosition);

    /**
     * Requests all diagram data in the database
     *
     * @return returns a list of all diagram objects in the database
     */
    List<Diagram> getAll();

    /**
     * Requests an update to a diagram object with the specified ID
     *
     * @param id the id of the diagram object to be updated
     * @param newDiagram the diagram object with the data to be updated
     */
    Diagram update(UUID id, Diagram newDiagram);

    /**
     * Requests an update to a diagram object with the specified node position
     *
     * @param nodePosition the node position of the diagram object to be updated
     * @param newDiagram the diagram object with the data to be updated
     */
    Diagram updateByNode(Diagram newDiagram, int nodePosition);

    /**
     * Requests the deletion of a diagram object with the specified node position
     *
     * @param nodePosition the node position of the diagram object to be deleted
     */
    int delete(int nodePosition);
}
