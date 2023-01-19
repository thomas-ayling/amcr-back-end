package com.globallogic.amcr.persistence.dao.pagecontent;

import com.globallogic.amcr.persistence.mapper.pagecontent.DiagramMapper;
import com.globallogic.amcr.repository.Dao;
import com.globallogic.amcr.persistence.model.pagecontent.Diagram;
import com.globallogic.amcr.utils.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class DiagramDao implements Dao<Diagram, Diagram> {

    private final Logger Log = LoggerFactory.getLogger(DiagramDao.class.getName());
    private final DiagramMapper diagramMapper;

    public DiagramDao (DiagramMapper diagramMapper) {
        this.diagramMapper = Assert.assertNotNull(diagramMapper, "Diagram mapper cannot be null");
    }
    /**
     * @param diagram the diagram object received from client, no ID set
     * @param diagramId the ID for the diagram object
     */
    @Override
    public Diagram save(Diagram diagram, UUID diagramId) {
            diagram.setId(diagramId);
            Log.trace("DAO saving new diagram data:\n{}", diagram);
            diagramMapper.save(diagram);
            return diagram;
    }

    /**
     * @param id the id of the diagram object to be retrieved from the database
     * @return returns the appropriate diagram entry
     */
    @Override
    public Diagram get(UUID id) {
        Log.trace("DAO requesting diagram data with ID {}", id);
        return diagramMapper.get(id);
    }

    /**
     * @param nodePosition the node id of the diagram object to be retrieved
     * @return returns the diagram entry with the appropriate node id
     */
    public Diagram getByNode(int nodePosition) {
        Log.trace("DAO requesting diagram data at node position {}", nodePosition);
        return diagramMapper.getByNode(nodePosition);
    }

    /**
     * @return returns all the diagram entries
     */
    @Override
    public List<Diagram> getAll() {
        Log.trace("DAO requesting all diagram data");
        return diagramMapper.getAll();
    }

    /**
     * @param id the id of the diagram object that will be updated
     * @param newDiagram the data the diagram object will be updated with
     * @param oldDiagram the data of the diagram object before it is updated
     */
    public Diagram update(UUID id, Diagram newDiagram, Diagram oldDiagram) {
        newDiagram.setId(id);
        if(oldDiagram.equals(newDiagram)) {
            return newDiagram;
        }
        if(newDiagram.getNodePosition() < 1 || newDiagram.getNodePosition() > 8) {
            newDiagram.setNodePosition(oldDiagram.getNodePosition());
        }
        if(newDiagram.getTitle() == null) {
            newDiagram.setTitle(oldDiagram.getTitle());
        }
        if(newDiagram.getBody() == null) {
            newDiagram.setBody(oldDiagram.getBody());
        }
        Log.trace("DAO updating diagram data with ID {} and content:\n{}\nwith new data:\n{}",id,oldDiagram,newDiagram);
        diagramMapper.update(id, newDiagram);
        return newDiagram;
    }

    /**
     * @param nodePosition the node position of the diagram object that will be updated
     * @param newDiagram the data the diagram object will be updated with
     * @param oldDiagram the data of the diagram object before it is updated
     */
    public Diagram updateByNode(int nodePosition, Diagram newDiagram, Diagram oldDiagram) {
        newDiagram.setNodePosition(nodePosition);
        if(newDiagram.getId() == null) {
            newDiagram.setId(oldDiagram.getId());
        }
        if(newDiagram.getTitle() == null) {
            newDiagram.setTitle(oldDiagram.getTitle());
        }
        if(newDiagram.getBody() == null) {
            newDiagram.setBody(oldDiagram.getBody());
        }
        Log.trace("DAO updating diagram data at node position {} and content:\n{}\nwith new data:\n{}",nodePosition,oldDiagram,newDiagram);
        diagramMapper.updateByNode(nodePosition, newDiagram);
        return newDiagram;
    }

    /**
     * @param nodePosition the node position of the diagram object that will be deleted
     */
    public int delete(int nodePosition) {
            Log.trace("DAO deleting diagram data at node position {}", nodePosition);
            diagramMapper.delete(nodePosition);
            return nodePosition;
    }
}
