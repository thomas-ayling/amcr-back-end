package com.globallogic.amcr.service.pagecontent;

import com.globallogic.amcr.persistence.model.pagecontent.Diagram;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface DiagramService {

    /**
     * @param diagram the diagram object with the data to be saved
     */
    void save(Diagram diagram);

    /**
     * @param id the id of the diagram object that will be returned
     * @return the diagram object with the specified id
     */
    Diagram get (UUID id);

    /**
     * @param nodeId the node id of the diagram object that will be returned
     * @return the diagram object with the specified node id
     */
    Diagram getByNode (int nodeId);

    /**
     * @return a list of all markdown objects in the table
     */
    List<Diagram> getAll();

    /**
     * @param diagram the diagram object with the data to be updated
     * @param id the id of the diagram object to be updated
     */
    void update(Diagram diagram, UUID id);

    /**
     * @param diagram the diagram object with the data to be updated
     * @param nodeId the node id of the diagram object to be updated
     */
    void updateByNode(Diagram diagram, int nodeId);

    /**
     * @param nodeId the node id of the diagram object to be deleted
     */
    void delete(int nodeId);
}
