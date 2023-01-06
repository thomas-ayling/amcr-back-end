package com.globallogic.amcr.controller.pagecontent;

import com.globallogic.amcr.persistence.model.pagecontent.Diagram;
import com.globallogic.amcr.service.pagecontent.DiagramServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * endpoint for diagram upload and download
 */
@RestController
@RequestMapping("/page-content/diagram")
@CrossOrigin(origins = "*")
public class DiagramController {

    private final DiagramServiceImpl diagramServiceImpl;

    public DiagramController(DiagramServiceImpl diagramServiceImpl) {
        this.diagramServiceImpl = diagramServiceImpl;
    }

    /**
     * @param diagram the diagram json object
     * @return returns a response entity either CREATED (201) or INTERNAL_SERVER_ERROR (500)
     */
    @PostMapping("/upload")
    public ResponseEntity saveDiagram(@RequestBody Diagram diagram) {
        diagramServiceImpl.save(diagram);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * @param id the id of the diagram object to be displayed
     * @return returns the diagram data related to the given id
     */
    @GetMapping("/get-by-id/{id}")
    public Diagram getByIdDiagram(@PathVariable UUID id) { return diagramServiceImpl.get(id);}

    /**
     * @param nodeId the node id of the diagram object to be displayed
     * @return returns the diagram data related to the given node id
     */
    @GetMapping("/get-by-node/{nodeId}")
    public Diagram getbyNodeDiagram(@PathVariable int nodeId) { return diagramServiceImpl.getByNode(nodeId);}

    /**
     * @return returns a list of all diagram data
     */
    @GetMapping("/get-all")
    public List<Diagram> getAllDiagram() { return diagramServiceImpl.getAll();}

    /**
     * @param id the id of the diagram entry to be updated
     * @param diagram the diagram object with the values that the database will be updated with
     */
    @PutMapping("/update/{id}")
    public ResponseEntity updateDiagram(@PathVariable UUID id, @RequestBody Diagram diagram) {
        diagramServiceImpl.update(diagram, id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    /**
     * @param nodeId the node id of the diagram entry to be updated
     * @param diagram the diagram object with the values that the database will be updated with
     */
    @PutMapping("/update-by-node/{nodeId}")
    public ResponseEntity updateByNodeDiagram(@PathVariable int nodeId, @RequestBody Diagram diagram) {
        diagramServiceImpl.updateByNode(diagram, nodeId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{nodeId}")
    public ResponseEntity deleteDiagram(@PathVariable int nodeId) {
        diagramServiceImpl.delete(nodeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}