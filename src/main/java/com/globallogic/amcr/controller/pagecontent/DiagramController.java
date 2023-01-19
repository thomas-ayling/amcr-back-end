package com.globallogic.amcr.controller.pagecontent;

import com.globallogic.amcr.model.pagecontent.Diagram;
import com.globallogic.amcr.service.impl.pagecontent.DiagramServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.UUID;

/**
 * endpoint for diagram upload and download
 */
@RestController
@RequestMapping("/page-content/diagram")
@CrossOrigin(origins = "*")
public class DiagramController {

    private final Logger Log = LoggerFactory.getLogger(DiagramController.class.getName());
    private final DiagramServiceImpl diagramServiceImpl;

    public DiagramController(DiagramServiceImpl diagramServiceImpl) {
        this.diagramServiceImpl = diagramServiceImpl;
    }

    /**
     * @param diagram the diagram json object
     * @return returns a response entity either CREATED (201) or INTERNAL_SERVER_ERROR (500)
     */
    @PostMapping(value="/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Diagram> saveDiagram(@RequestBody Diagram diagram) {
        Log.debug("Controller saving new diagram data");
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(diagramServiceImpl.save(diagram)).toUri()).body(diagramServiceImpl.save(diagram));
    }

    /**
     * @param id the id of the diagram object to be displayed
     * @return returns the diagram data related to the given id
     */
    @GetMapping(value="/{id}", produces = "application/json")
    public ResponseEntity<Diagram> getByIdDiagram(@PathVariable UUID id) {
        Log.debug("Controller requesting diagram data with ID {}", id);
        return ResponseEntity.ok().body(diagramServiceImpl.get(id));
    }

    /**
     * @param nodePosition the node id of the diagram object to be displayed
     * @return returns the diagram data related to the given node id
     */
    @GetMapping(value="/node/{nodePosition}", produces = "application/json")
    public ResponseEntity<Diagram> getbyNodeDiagram(@PathVariable int nodePosition) {
        Log.debug("Controller requesting diagram data at node position {}", nodePosition);
        return ResponseEntity.ok().body(diagramServiceImpl.getByNode(nodePosition));
    }
    /**
     * @return returns a list of all diagram data
     */
    @GetMapping(value="/", produces = "application/json")
    public ResponseEntity<List<Diagram>> getAllDiagram() {
        Log.debug("Controller requesting all diagram data");
        return ResponseEntity.ok().body(diagramServiceImpl.getAll());
    }

    /**
     * @param id      the id of the diagram entry to be updated
     * @param diagram the diagram object with the values that the database will be updated with
     */
    @PutMapping(value="/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Diagram> updateDiagram(@PathVariable UUID id, @RequestBody Diagram diagram) {
        Log.debug("Controller updating diagram data with ID {}", id);
        return ResponseEntity.accepted().body(diagramServiceImpl.update(id, diagram));
    }

    /**
     * @param nodePosition  the node position of the diagram entry to be updated
     * @param diagram the diagram object with the values that the database will be updated with
     */
    @PutMapping(value="/node/{nodePosition}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Diagram> updateByNodeDiagram(@PathVariable int nodePosition, @RequestBody Diagram diagram) {
        Log.debug("Controller updating diagram data at node position {}", nodePosition);
        return ResponseEntity.accepted().body(diagramServiceImpl.updateByNode(diagram, nodePosition));
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<UUID> deleteDiagram(@PathVariable UUID id) {
        Log.debug("Controller requesting deletion of diagram  data with ID {}", id);
        diagramServiceImpl.delete(id);
        return ResponseEntity.noContent().build();
    }
}