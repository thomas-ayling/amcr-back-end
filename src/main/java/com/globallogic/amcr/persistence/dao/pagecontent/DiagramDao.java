package com.globallogic.amcr.persistence.dao.pagecontent;

import com.globallogic.amcr.mapper.pagecontent.DiagramMapper;
import com.globallogic.amcr.persistence.dao.Dao;
import com.globallogic.amcr.persistence.model.pagecontent.Diagram;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class DiagramDao implements Dao<Diagram, Diagram> {

    DiagramMapper diagramMapper;

    public DiagramDao (DiagramMapper diagramMapper) {this.diagramMapper = diagramMapper;}
    /**
     * @param diagram the diagram object received from client, no ID set
     * @param diagramId the ID for the diagram object
     */
    @Override
    public void save(Diagram diagram, UUID diagramId) {
        try {
            diagram.setId(diagramId);
            diagramMapper.save(diagram);
        } catch (Exception e){
            throw new RuntimeException("Could not save diagram data", e);
        }
    }

    /**
     * @param id the id of the diagram object to be retrieved from the database
     * @return returns the appropriate diagram entry
     */
    @Override
    public Diagram get(UUID id) {return diagramMapper.get(id);}

    /**
     * @param nodeId the node id of the diagram object to be retrieved
     * @return returns the diagram entry with the appropriate node id
     */
    public Diagram getByNode(int nodeId) {return diagramMapper.getByNode(nodeId);}

    /**
     * @return returns all the diagram entries
     */
    @Override
    public List<Diagram> getAll() {return diagramMapper.getAll();}

    /**
     * @param diagram the diagram object that will be updated
     * @param id the id of the diagram object that will be updated
     */
    public void update(Diagram diagram, UUID id) {
        try {
            diagram.setId(id);
            diagramMapper.update(diagram);
        } catch (Exception e) {
            throw new RuntimeException("Could not update diagram data", e);
        }
    }

    /**
     * @param diagram the diagram object that will be updated
     * @param nodeId the node id of the diagram object that will be updated
     */
    public void updateByNode(Diagram diagram, int nodeId) {
        try {
            diagram.setNodeId(nodeId);
            diagramMapper.updateByNode(diagram);
        } catch (Exception e) {
            throw new RuntimeException("Could not delete diagram data", e);
        }
    }

    /**
     * @param nodeId the node id of the diagram object that will be deleted
     */
    public void delete(int nodeId) {
        try {
            diagramMapper.delete(nodeId);
        } catch (Exception e) {
            throw new RuntimeException("Could not delete diagram data", e);
        }
    }
}
