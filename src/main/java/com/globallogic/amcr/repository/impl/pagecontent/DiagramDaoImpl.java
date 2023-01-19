package com.globallogic.amcr.repository.impl.pagecontent;

import com.globallogic.amcr.model.pagecontent.Diagram;
import com.globallogic.amcr.repository.pagecontent.DiagramDao;
import com.globallogic.amcr.utils.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class DiagramDaoImpl implements DiagramDao {

    private final Logger Log = LoggerFactory.getLogger(DiagramDaoImpl.class.getName());
    private final DiagramMapper diagramMapper;

    public DiagramDaoImpl(DiagramMapper diagramMapper) {
        this.diagramMapper = Assert.assertNotNull(diagramMapper, "Diagram mapper cannot be null");
    }

    @Override
    public Diagram save(Diagram diagram, UUID diagramId) {
            diagram.setId(diagramId);
            Log.trace("DAO saving new diagram data:\n{}", diagram);
            diagramMapper.save(diagram);
            return diagram;
    }

    @Override
    public Diagram get(UUID id) {
        Log.trace("DAO requesting diagram data with ID {}", id);
        return diagramMapper.get(id);
    }

    @Override
    public Diagram getByNode(int nodePosition) {
        Log.trace("DAO requesting diagram data at node position {}", nodePosition);
        return diagramMapper.getByNode(nodePosition);
    }

    @Override
    public List<Diagram> getAll() {
        Log.trace("DAO requesting all diagram data");
        return diagramMapper.getAll();
    }

    @Override
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

    @Override
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

    @Override
    public void delete(UUID id) {
            Log.trace("DAO deleting diagram data at node position {}", id);
            diagramMapper.delete(id);
    }
}
