package com.globallogic.amcr.repository.pagecontent;

import com.globallogic.amcr.model.pagecontent.Diagram;
import com.globallogic.amcr.repository.CrudDao;

public interface DiagramDao extends CrudDao<Diagram, Diagram> {

    /**
     * @param nodePosition the node id of the diagram object to be retrieved
     * @return returns the diagram entry with the appropriate node id
     */
    Diagram getByNode(int nodePosition);

    /**
     * @param nodePosition the node position of the diagram object that will be updated
     * @param newDiagram the data the diagram object will be updated with
     * @param oldDiagram the data of the diagram object before it is updated
     */
    Diagram updateByNode(int nodePosition, Diagram newDiagram, Diagram oldDiagram);
}
