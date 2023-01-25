package com.globallogic.amcr.controller.casestudies;

import com.globallogic.amcr.exception.NotFoundException;
import com.globallogic.amcr.model.casestudies.CaseStudy;
import com.globallogic.amcr.model.casestudies.CaseStudyOverview;
import com.globallogic.amcr.service.casestudies.CaseStudyService;
import com.globallogic.amcr.utils.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/case-study")
@CrossOrigin
public class CaseStudyController {
    private final Logger Log = LoggerFactory.getLogger(CaseStudyController.class);
    private final CaseStudyService caseStudyService;

    public CaseStudyController(CaseStudyService caseStudyService) {
        this.caseStudyService = Assert.assertNotNull(caseStudyService, "Case study service cannot be null");
    }

    @CrossOrigin(exposedHeaders = "Location")
    @PostMapping(value = "/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<CaseStudy> save(@RequestBody @Validated CaseStudy caseStudy, BindingResult errors) {
        if (errors.hasErrors()) {
            throw new NotFoundException(errors.toString());
        }
        Log.debug("Controller saving new case study");
        CaseStudy createdCaseStudy = caseStudyService.save(caseStudy);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdCaseStudy.getId()).toUri()).body(createdCaseStudy);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<CaseStudy> get(@PathVariable UUID id) {
        Log.debug("Controller requesting case study with ID {}", id);
        return ResponseEntity.ok().body(caseStudyService.get(id));
    }

    @GetMapping(value = "/{id}/attachment/{attachmentId}")
    public ModelAndView getAttachment(@PathVariable UUID id, @PathVariable UUID attachmentId) {
        return new ModelAndView("forward:/attachment/" + attachmentId);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<CaseStudy>> getAll() {
        Log.debug("Controller requesting all case studies");
        return ResponseEntity.ok().body(caseStudyService.getAll());
    }

    @GetMapping(value = "/overviews", produces = "application/json")
    public ResponseEntity<List<CaseStudyOverview>> getAllOverviews(@RequestParam(required = false) Boolean spotlit, @RequestParam(required = false) Boolean latest, @RequestParam(required = false) Integer entries) {
        spotlit = spotlit != null && spotlit;
        latest = latest != null && latest;
        entries = entries == null ? 5 : entries;

        if (spotlit && latest) {
            throw new RuntimeException("Spotlit and Latest cannot both be true. Choose one and try again.");
        }
        Log.debug(spotlit ? "Controller requesting all spotlit case study overviews" : latest ? "Controller requesting latest case study overviews" : "Controller requesting all case study overviews");
        return ResponseEntity.ok().body(spotlit ? caseStudyService.getSpotlitOverviews() : latest ? caseStudyService.getLatestOverviews(entries) : caseStudyService.getAllOverviews());
    }

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<CaseStudy> update(@PathVariable UUID id, @RequestBody CaseStudy newCaseStudy) {
        Log.debug("Controller updating case study with ID {}", id);
        return ResponseEntity.accepted().body(caseStudyService.update(id, newCaseStudy));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        Log.debug("Controller requesting deletion of case study with ID {}", id);
        caseStudyService.delete(id);
        return ResponseEntity.noContent().build();
    }
}


