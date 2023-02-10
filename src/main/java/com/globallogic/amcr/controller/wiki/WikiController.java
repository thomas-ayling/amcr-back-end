package com.globallogic.amcr.controller.wiki;


import com.globallogic.amcr.model.wiki.Wiki;
import com.globallogic.amcr.service.wikicomponent.WikiService;
import com.globallogic.amcr.utils.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/wiki")
@CrossOrigin
public class WikiController {

    private final Logger log = LoggerFactory.getLogger(WikiController.class);

    private final WikiService wikiService;

    public WikiController(WikiService wikiService) {
        this.wikiService = Assert.assertNotNull(wikiService, "Wiki page service cannot be null");

    }

    @PostMapping(value = "/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Wiki> saveWiki(@RequestBody @Validated Wiki Wiki, BindingResult errors) {
        if (errors.hasErrors()) {
            throw new IllegalArgumentException(errors.toString());
        }
        log.debug("Controller saving new Wiki page");
        Wiki createdWiki = wikiService.save(Wiki);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdWiki.getId()).toUri()).body(createdWiki);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Wiki> get(@PathVariable UUID id) {
        log.debug("Controller requesting Wiki page with ID {}", id);
        return ResponseEntity.ok().body(wikiService.get(id));
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<Wiki>> getAll() {
        log.debug("Controller requesting all Wiki pages");
        return ResponseEntity.ok().body(wikiService.getAll());
    }

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Wiki> update(@PathVariable UUID id, @RequestBody Wiki newWiki) {
        log.debug("Controller updating Wiki page with ID {}", id);
        return ResponseEntity.accepted().body(wikiService.update(id, newWiki));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        log.debug("Controller requesting deletion of case study with ID {}", id);
        wikiService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
