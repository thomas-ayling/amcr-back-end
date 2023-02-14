package com.globallogic.amcr.service.impl.pagecontent;

import com.globallogic.amcr.model.pagecontent.Diagram;
import com.globallogic.amcr.repository.pagecontent.DiagramDao;
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

    private final DiagramDao diagramDao;
    private final Logger log = LoggerFactory.getLogger(DiagramServiceImpl.class.getName());

    public DiagramServiceImpl(DiagramDao diagramDao) {
        this.diagramDao = Assert.assertNotNull(diagramDao, "DiagramDao cannot be null");
    }

    @Transactional
    public Diagram save(Diagram diagram) {
        Assert.assertNotNull(diagram, "Service: Diagram data cannot be null");
        UUID id = UUID.randomUUID();
        log.debug("Service: Saving new diagram data");
        return diagramDao.save(diagram, id);
    }

    @Transactional(readOnly = true)
    public Diagram get(UUID id) {
        Assert.assertNotNull(id, "Service: ID cannot be null");
        log.debug("Service requesting diagram data with ID {}", id);
        return diagramDao.get(id);
    }

    @Transactional(readOnly = true)
    public List<Diagram> getAll() {
        log.debug("Service requesting all diagram data");
        return diagramDao.getAll();
    }

    @Transactional
    public Diagram update(UUID id, Diagram newDiagram) {
        Assert.assertNotNull(id, "Service: ID must be included to update the diagram");
        Assert.assertNotNull(newDiagram, "Service: New diagram data must be included to update");

        final Diagram oldDiagram = Assert.assertNotNull(diagramDao.get(id), "Object with specified ID could not be found");
        log.debug("Service updating diagram data with ID {}", id);
        return diagramDao.update(id, newDiagram, oldDiagram);
    }

    @Transactional
    public void delete(UUID id) {
        Assert.assertNotNull(id, "ID cannot be null to delete entry");
        log.debug("Service requesting deletion of diagram data with ID {}", id);
        diagramDao.delete(id);
    }
}