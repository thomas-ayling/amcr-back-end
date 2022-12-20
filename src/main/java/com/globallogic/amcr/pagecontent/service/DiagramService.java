package com.globallogic.amcr.pagecontent.service;

import com.globallogic.amcr.pagecontent.mapper.DiagramMapper;
import com.globallogic.amcr.pagecontent.persistence.model.Diagram;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiagramService {

    public final DiagramMapper diagramMapper;

    public DiagramService(DiagramMapper diagramMapper) {
        this.diagramMapper = diagramMapper;
    }

    public ResponseEntity<Resource> saveDiagram(Diagram diagram) {
        try {
            diagramMapper.save(diagram);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Diagram getByIdDiagram(int id) { return diagramMapper.getById(id);}

    public Diagram getByNodeDiagram(int nodeId) { return diagramMapper.getByNode(nodeId);}
    public List<Diagram> getAllDiagram() {return diagramMapper.getAll();}

    public ResponseEntity<Resource> updateDiagram(Diagram diagram) {
        try {
            diagramMapper.update(diagram);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
