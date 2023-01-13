package com.globallogic.amcr.service.pagecontent;

import com.globallogic.amcr.persistence.model.pagecontent.Diagram;

import java.util.List;
import java.util.UUID;

public interface DiagramService {

    /**
     * @param diagram the diagram object with the data to be saved
     */
    Diagram save(Diagram diagram);

    /**
     * @param id the id of the diagram object that will be returned
     * @return the diagram object with the specified id
     */
    Diagram get (UUID id);

    /**
     * @param nodePosition the node id of the diagram object that will be returned
     * @return the diagram object with the specified node id
     */
    Diagram getByNode (int nodePosition);

    /**
     * @return a list of all markdown objects in the table
     */
    List<Diagram> getAll();

    /**
     * @param newDiagram the diagram object with the data to be updated
     * @param id the id of the diagram object to be updated
     */
    Diagram update(UUID id, Diagram newDiagram);

    /**
     * @param newDiagram the diagram object with the data to be updated
     * @param nodePosition the node id of the diagram object to be updated
     */
    Diagram updateByNode(Diagram newDiagram, int nodePosition);

    /**
     * @param nodePosition the node id of the diagram object to be deleted
     */
    void delete(int nodePosition);
}
