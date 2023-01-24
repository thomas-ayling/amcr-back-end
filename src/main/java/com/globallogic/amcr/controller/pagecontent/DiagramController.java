package com.globallogic.amcr.controller.pagecontent;

import com.globallogic.amcr.model.pagecontent.Diagram;
import com.globallogic.amcr.service.pagecontent.DiagramService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.UUID;

/**
 * endpoint for diagram upload and download
 */
@RestController
@RequestMapping("/diagram")
@CrossOrigin
public class DiagramController {

    private final Logger Log = LoggerFactory.getLogger(DiagramController.class.getName());
    private final DiagramService diagramService;

    public DiagramController(DiagramService diagramService) {
        this.diagramService = diagramService;
    }

    /**
     * @param diagram the diagram json object
     * @return returns a response entity either CREATED (201) or INTERNAL_SERVER_ERROR (500)
     */
    @PostMapping(value="/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Diagram> saveDiagram(@RequestBody @Validated Diagram diagram) {
        Log.debug("Controller saving new diagram data");
        Diagram createdDiagram = diagramService.save(diagram);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdDiagram).toUri()).body(createdDiagram);
    }

    /**
     * @param id the id of the diagram object to be displayed
     * @return returns the diagram data related to the given id
     */
    @GetMapping(value="/{id}", produces = "application/json")
    public ResponseEntity<Diagram> getByIdDiagram(@PathVariable UUID id) {
        Log.debug("Controller requesting diagram data with ID {}", id);
        return ResponseEntity.ok().body(diagramService.get(id));
    }

    /**
     * @return returns a list of all diagram data
     */
    @GetMapping(value="/", produces = "application/json")
    public ResponseEntity<List<Diagram>> getAllDiagram() {
        Log.debug("Controller requesting all diagram data");
        return ResponseEntity.ok().body(diagramService.getAll());
    }

    /**
     * @param id      the id of the diagram entry to be updated
     * @param diagram the diagram object with the values that the database will be updated with
     */
    @PutMapping(value="/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Diagram> updateDiagram(@PathVariable UUID id, @RequestBody Diagram diagram) {
        Log.debug("Controller updating diagram data with ID {}", id);
        return ResponseEntity.accepted().body(diagramService.update(id, diagram));
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> deleteDiagram(@PathVariable UUID id) {
        Log.debug("Controller requesting deletion of diagram  data with ID {}", id);
        diagramService.delete(id);
        return ResponseEntity.noContent().build();
    }
}