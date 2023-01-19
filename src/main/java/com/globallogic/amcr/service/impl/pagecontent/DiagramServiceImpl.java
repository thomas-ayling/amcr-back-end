package com.globallogic.amcr.service.impl.pagecontent;

import com.globallogic.amcr.repository.impl.pagecontent.DiagramDaoImpl;
import com.globallogic.amcr.model.pagecontent.Diagram;
import com.globallogic.amcr.service.pagecontent.DiagramService;
import com.globallogic.amcr.utils.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class DiagramServiceImpl implements DiagramService {

    private final DiagramDaoImpl diagramDaoImpl;
    private  final Logger Log = LoggerFactory.getLogger(DiagramServiceImpl.class.getName());

    public DiagramServiceImpl(DiagramDaoImpl diagramDaoImpl) {
        this.diagramDaoImpl = Assert.assertNotNull(diagramDaoImpl, "DiagramDao cannot be null");
    }

    @Transactional
    public Diagram save(Diagram diagram) {
        Assert.assertNotNull(diagram, "Service: Diagram data cannot be null");
        try {
            UUID diagramId = UUID.randomUUID();
            Log.debug("Service: Saving new diagram data");
            return diagramDaoImpl.save(diagram, diagramId);
        } catch (Exception e) {
            throw new RuntimeException("Error in Service Implementation - Could not save diagram", e);
        }
    }

    @Transactional(readOnly = true)
    public Diagram get(UUID id) {
        Assert.assertNotNull(id, "Service: ID cannot be null");
        Log.debug("Service requesting diagram data with ID {}", id);
        return diagramDaoImpl.get(id);
    }

    @Transactional(readOnly = true)
    public Diagram getByNode(int nodePosition) {
        Assert.assertNotNull(nodePosition, "Service: Node position variable cannot be null");
        Log.debug("Service requesting diagram data with node position {}", nodePosition);
        return diagramDaoImpl.getByNode(nodePosition);
    }
    @Transactional(readOnly = true)
    public List<Diagram> getAll() {
        Log.debug("Service requesting all diagram data");
        return diagramDaoImpl.getAll();
    }

    @Transactional
    public Diagram update(UUID id, Diagram newDiagram) {
        Assert.assertNotNull(id, "Service: ID must be included to update the diagram");
        Assert.assertNotNull(newDiagram, "Service: New diagram data must be included to update");

        Diagram oldDiagram = Assert.assertNotNull(diagramDaoImpl.get(id), "Object with specified ID could not be found");
        Log.debug("Service updating diagram data with ID {}", id);
        return diagramDaoImpl.update(id, newDiagram, oldDiagram);
    }

    @Transactional
    public Diagram updateByNode(Diagram newDiagram, int nodePosition) {
        Assert.assertNotNull(nodePosition, "Service: Node position must be included to update the diagram");
        Assert.assertNotNull(newDiagram, "Service: New diagram object must be included to update");

        Diagram oldDiagram = Assert.assertNotNull(diagramDaoImpl.getByNode(nodePosition), "Object with specified node position could not be found");
        Log.debug("Service updating case study with node position {}", nodePosition);
        return diagramDaoImpl.updateByNode(nodePosition, newDiagram, oldDiagram);
    }

    @Transactional
    public void delete(UUID id) {
        Assert.assertNotNull(id, "ID cannot be null to delete entry");
        Log.debug("Service requesting deletion of diagram data with ID {}", id);
    }
}