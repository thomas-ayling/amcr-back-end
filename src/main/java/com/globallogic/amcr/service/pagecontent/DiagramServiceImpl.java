package com.globallogic.amcr.service.pagecontent;

import com.globallogic.amcr.mapper.pagecontent.DiagramMapper;
import com.globallogic.amcr.persistence.dao.pagecontent.DiagramDao;
import com.globallogic.amcr.persistence.model.pagecontent.Diagram;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class DiagramServiceImpl implements DiagramService{

    public final DiagramDao diagramDao;

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
    public void update(Diagram diagram, UUID id) {
        try {
            diagramDao.update(diagram, id);
        } catch (Exception e){
            throw new RuntimeException("Error updating in service implementation", e);
        }
    }

    @Transactional
    public void updateByNode(Diagram diagram, int nodeId) {
        try {
            diagramDao.updateByNode(diagram, nodeId);
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