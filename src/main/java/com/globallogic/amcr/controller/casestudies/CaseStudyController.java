package com.globallogic.amcr.controller.casestudies;

import com.globallogic.amcr.controller.WebUtil;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/case-study")
@CrossOrigin
public class CaseStudyController {
    private final Logger log = LoggerFactory.getLogger(CaseStudyController.class);
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
        log.debug("Controller saving new case study");
        CaseStudy createdCaseStudy = caseStudyService.save(caseStudy);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdCaseStudy.getId()).toUri()).body(createdCaseStudy);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<CaseStudy> get(@PathVariable UUID id) {
        log.debug("Controller requesting case study with ID {}", id);
        CaseStudy caseStudy = caseStudyService.get(id);
        List<String> attachmentLinks = new ArrayList<>();
        if (caseStudy.getAttachmentIds() != null) {
            for (UUID attachmentId : caseStudy.getAttachmentIds()) {
                attachmentLinks.add(WebUtil.generateUri("/attachment/{id}", attachmentId).toString());
            }
        }
        for (Map<String, String> row : caseStudy.getBody()) {
            row.replace("imageId", WebUtil.generateUri("/attachment/{id}", UUID.fromString(row.get("imageId"))).toString());
        }
        caseStudy.setAttachmentLinks(attachmentLinks);
        caseStudy.setCoverImageLink(WebUtil.generateUri("/attachment/{id}", caseStudy.getCoverImageId()).toString());
        return ResponseEntity.ok().body(caseStudy);
    }

    @GetMapping(value = "/{id}/attachment/{attachmentId}")
    public ModelAndView getAttachment(@PathVariable UUID id, @PathVariable UUID attachmentId) {
        log.debug("Controller forwarding request to /attachment/{}", attachmentId);
        return new ModelAndView("forward:/attachment/" + attachmentId);
    }

    @GetMapping(value = "/overviews/attachment/{attachmentId}")
    public ModelAndView getAttachment(@PathVariable UUID attachmentId) {
        log.debug("Controller forwarding request to /attachment/{}", attachmentId);
        return new ModelAndView("forward:/attachment/" + attachmentId);
    }

    @GetMapping(value = "/{id}/attachment/{attachmentId}/metadata")
    public ModelAndView getAttachmentMetadata(@PathVariable UUID id, @PathVariable UUID attachmentId) {
        log.debug("Controller forwarding request to /attachment/metadata/{}", attachmentId);
        return new ModelAndView("forward:/attachment/metadata/" + attachmentId);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<CaseStudy>> getAll() {
        log.debug("Controller requesting all case studies");
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
        log.debug(spotlit ? "Controller requesting all spotlit case study overviews" : latest ? "Controller requesting latest case study overviews" : "Controller requesting all case study overviews");
        List<CaseStudyOverview> caseStudyOverviews = spotlit ? caseStudyService.getSpotlitOverviews() : latest ? caseStudyService.getLatestOverviews(entries) : caseStudyService.getAllOverviews();
        for (CaseStudyOverview caseStudyOverview : caseStudyOverviews) {
            caseStudyOverview.setCoverImageLink(WebUtil.generateUri("/attachment/{id}", caseStudyOverview.getCoverImageId()).toString());
        }
        return ResponseEntity.ok().body(caseStudyOverviews);
    }

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<CaseStudy> update(@PathVariable UUID id, @RequestBody CaseStudy newCaseStudy) {
        log.debug("Controller updating case study with ID {}", id);
        return ResponseEntity.accepted().body(caseStudyService.update(id, newCaseStudy));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        log.debug("Controller requesting deletion of case study with ID {}", id);
        caseStudyService.delete(id);
        return ResponseEntity.noContent().build();
    }
}


