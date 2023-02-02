package com.globallogic.amcr.controller.wikipage;


import com.globallogic.amcr.exception.contactcomponent.NotFoundException;
import com.globallogic.amcr.model.wikipage.WikiPage;
import com.globallogic.amcr.service.wikipagecomponent.WikiPageService;
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
@RequestMapping("/wikipage")
@CrossOrigin
public class WikiPageController {

    private final Logger Log = LoggerFactory.getLogger(WikiPageController.class);

    private final WikiPageService wikiPageService;

    public WikiPageController(WikiPageService wikiPageService) {
        this.wikiPageService = Assert.assertNotNull(wikiPageService, "Wiki page service cannot be null");

    }

    @PostMapping(value = "/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<WikiPage> saveWikiPage(@RequestBody @Validated WikiPage wikiPage, BindingResult errors){
        if (errors.hasErrors()) {
            throw new NotFoundException(errors.toString());
        }
        Log.debug("Controller saving new wiki page");
        WikiPage createdWikiPage = wikiPageService.save(wikiPage);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdWikiPage.getId()).toUri()).body(createdWikiPage);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<WikiPage> get(@PathVariable UUID id) {
        Log.debug("Controller requesting wiki page with ID {}", id);
        return ResponseEntity.ok().body(wikiPageService.get(id));
    }
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<WikiPage>> getAll(){
        Log.debug("Controller requesting all wiki pages");
        return ResponseEntity.ok().body(wikiPageService.getAll());
    }
    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<WikiPage> update(@PathVariable UUID id, @RequestBody WikiPage newWikiPage) {
        Log.debug("Controller updating wiki page with ID {}", id);
        return ResponseEntity.accepted().body(wikiPageService.update(id, newWikiPage));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        Log.debug("Controller requesting deletion of case study with ID {}", id);
        wikiPageService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
