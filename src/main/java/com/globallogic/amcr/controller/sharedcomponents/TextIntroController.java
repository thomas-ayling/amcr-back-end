package com.globallogic.amcr.controller.sharedcomponents;

import com.globallogic.amcr.model.pagecontent.Diagram;
import com.globallogic.amcr.model.sharedcomponents.TextIntro;
import com.globallogic.amcr.service.pagecontent.DiagramService;
import com.globallogic.amcr.service.sharedcomponents.TextIntroService;
import com.globallogic.amcr.utils.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/text-intro")
@CrossOrigin
public class TextIntroController {
    private final Logger LOG = LoggerFactory.getLogger(TextIntroController.class);
    private final TextIntroService textIntroService;

    public TextIntroController(TextIntroService textIntroService) {
        this.textIntroService = textIntroService;
    }

    @PostMapping(value="/", consumes = "application/json", produces="application/json")
    public ResponseEntity<TextIntro> saveTextIntro(@RequestBody @Validated TextIntro textIntro){
        LOG.debug("Controller saving new Text Intro");
        TextIntro newTextIntro = textIntroService.save(textIntro);
        return ResponseEntity.accepted().body(newTextIntro);
    }

    @GetMapping(value="/{location}", produces="application/json")
    public ResponseEntity<TextIntro> getByLocation(@PathVariable String location) {
        LOG.debug("Controller requesting text intro for location {}", location);
        return ResponseEntity.ok().body(textIntroService.getByLocation(location));
    }

    @GetMapping(value="/{id}", produces="application/json")
    public ResponseEntity<TextIntro> getById(@PathVariable UUID id) {
        LOG.debug("Controller requesting text intro for ID {}", id);
        return ResponseEntity.ok().body(textIntroService.get(id));
    }

    @PutMapping(value="/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<TextIntro> updateTextIntro(@PathVariable UUID id, @RequestBody TextIntro textIntro) {
        LOG.debug("Controller updating Text Intro data with ID {}", id);
        return ResponseEntity.accepted().body(textIntroService.update(id, textIntro));
    }

    @DeleteMapping(value="/{id}", produces="application/json")
    public ResponseEntity<?> deleteTextIntro(@PathVariable UUID id) {
        LOG.debug("Controller requesting deletion of text intro with ID {}", id);
        textIntroService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
