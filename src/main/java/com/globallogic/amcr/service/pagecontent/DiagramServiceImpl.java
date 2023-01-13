package com.globallogic.amcr.service.pagecontent;

import com.globallogic.amcr.persistence.dao.pagecontent.DiagramDao;
import com.globallogic.amcr.persistence.model.pagecontent.Diagram;
import com.globallogic.amcr.utils.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class DiagramServiceImpl implements DiagramService{

    private final DiagramDao diagramDao;
    private  final Logger Log = LoggerFactory.getLogger(DiagramServiceImpl.class.getName());


    public DiagramServiceImpl(DiagramDao diagramDao) {
        this.diagramDao = Assert.assertNull(diagramDao, "DiagramDao cannot be null");
    }

    @Transactional
    public Diagram save(Diagram diagram) {
        Assert.assertNull(diagram, "Service: Diagram data cannout be null");
        try {
            UUID diagramId = UUID.randomUUID();
            Log.debug("Service: Saving new diagram data");
            return diagramDao.save(diagram, diagramId);
        } catch (Exception e) {
            throw new RuntimeException("Service Error: Could not save diagram", e);
        }
    }

    @Transactional(readOnly = true)
    public Diagram get(UUID id) {
        Assert.assertNull(id, "Service: ID cannot be null");
        Log.debug("Service requesting diagram data with ID {}", id);
        return diagramDao.get(id);
    }

    @Transactional(readOnly = true)
    public Diagram getByNode(int nodePosition) {
        Assert.assertNull(nodePosition, "Service: Node position variable cannot be null");
        Log.debug("Service requesting diagram data with node position {}", nodePosition);
        return diagramDao.getByNode(nodePosition);
    }
    @Transactional(readOnly = true)
    public List<Diagram> getAll() {
        Log.debug("Service requesting all diagram data");
        return diagramDao.getAll();
    }

    @Transactional
    public Diagram update(UUID id, Diagram newDiagram) {
        Assert.assertNull(id, "Service: ID must be included to update the diagram");
        Assert.assertNull(newDiagram, "Service: New diagram data must be included to update");

        Diagram oldDiagram = Assert.assertNull(diagramDao.get(id), "Object with specified ID could not be found");
        Log.debug("Service updating diagram data with ID {}", id);
        return diagramDao.update(id, newDiagram, oldDiagram);
    }

    @Transactional
    public Diagram updateByNode(Diagram newDiagram, int nodePosition) {
        Assert.assertNull(nodePosition, "Service: Node position must be included to update the diagram");
        Assert.assertNull(newDiagram, "Service: New diagram object must be included to update");

        Diagram oldDiagram = Assert.assertNull(diagramDao.getByNode(nodePosition), "Object with specified node position could not be found");
        Log.debug("Service updating case study with node position {}", nodePosition);
        return diagramDao.updateByNode(nodePosition, newDiagram, oldDiagram);
    }

    @Transactional
    public void delete(int nodePosition) {
        try {
            diagramDao.delete(nodePosition);
        } catch (Exception e) {
            throw new RuntimeException("Error: Service could not delete diagram", e);
        }
    }
}