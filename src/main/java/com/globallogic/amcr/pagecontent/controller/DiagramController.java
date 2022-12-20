package com.globallogic.amcr.pagecontent.controller;

import com.globallogic.amcr.pagecontent.persistence.model.Diagram;
import com.globallogic.amcr.pagecontent.service.DiagramService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/page-content/diagram")
@CrossOrigin(origins = "*")
public class DiagramController {

    private final DiagramService diagramService;

    public DiagramController(DiagramService diagramService) {
        this.diagramService = diagramService;
    }

    @PostMapping("/upload-diagram")
    public ResponseEntity uploadDiagram(@RequestBody Diagram diagram) {
        return diagramService.saveDiagram(diagram);
    }

    @GetMapping("/get-by-id/{id}")
    public Diagram getByIdDiagram(@PathVariable int id) { return diagramService.getByIdDiagram(id);}

    @GetMapping("/get-by-node/{nodeId}")
    public Diagram getbyNodeDiagram(@PathVariable int nodeId) { return diagramService.getByNodeDiagram(nodeId);}

    @GetMapping("/get-all")
    public List<Diagram> getManyDiagram() { return diagramService.getAllDiagram();}

    @PutMapping("/update/{id}")
    public ResponseEntity updateDiagram(@PathVariable int id, @RequestBody Diagram diagram) {
        return diagramService.updateDiagram(diagram);
    }
}
