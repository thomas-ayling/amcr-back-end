package com.globallogic.amcr.service.pagecontent;

import com.globallogic.amcr.mapper.pagecontent.DiagramMapper;
import com.globallogic.amcr.persistence.dao.pagecontent.DiagramDao;
import com.globallogic.amcr.persistence.model.pagecontent.Diagram;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class DiagramServiceImpl implements DiagramService{

    private final DiagramDao diagramDao;
    private  final Logger Log = LoggerFactory.getLogger(DiagramServiceImpl.class.getName());


    public DiagramServiceImpl(DiagramDao diagramDao) {
        this.diagramDao = diagramDao;
    }

    @Transactional
    public void save(Diagram diagram) {
        try {
            UUID diagramId = UUID.randomUUID();
            diagramDao.save(diagram, diagramId);
        } catch (Exception e) {
            throw new RuntimeException("Error saving in service implementation", e);
        }
    }

    @Transactional(readOnly = true)
    public Diagram get(UUID id) { return diagramDao.get(id);}

    @Transactional(readOnly = true)
    public Diagram getByNode(int nodeId) { return diagramDao.getByNode(nodeId);}
    @Transactional(readOnly = true)
    public List<Diagram> getAll() {return diagramDao.getAll();}

    @Transactional
    public Diagram update(UUID id, Diagram newDiagram) {
        Objects.requireNonNull(id, "ID must be included to update the diagram");

        Diagram oldDiagram = Objects.requireNonNull(diagramDao.get(id), "Object with specified ID could not be found. Revise ID and try again");
        Log.debug("Service updating case study with ID {}", id);
        return diagramDao.update(id, newDiagram, oldDiagram);
    }

    @Transactional
    public Diagram updateByNode(Diagram newDiagram, int nodeId) {
        try {
            diagramDao.updateByNode(newDiagram, nodeId);
        } catch (Exception e) {
            throw new RuntimeException("Error updating by node ID in service implementation", e);
        }
    }

    @Transactional
    public void delete(int nodeId) {
        try {
            diagramDao.delete(nodeId);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting in service implementation", e);
        }
    }
}