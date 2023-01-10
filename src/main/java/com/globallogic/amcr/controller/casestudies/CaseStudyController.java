package com.globallogic.amcr.controller.casestudies;

import com.globallogic.amcr.exception.NotFoundException;
import com.globallogic.amcr.persistence.model.casestudies.CaseStudy;
import com.globallogic.amcr.persistence.model.casestudies.CaseStudyOverview;
import com.globallogic.amcr.service.casestudies.CaseStudyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/case-study")
@CrossOrigin(origins = {"http://localhost:3000", "http://ec-acad-elb-fe-2f7bfb1526a617ef.elb.eu-west-2.amazonaws.com:3000"})
public class CaseStudyController {
    public static final Logger LOGGER = LoggerFactory.getLogger(CaseStudyController.class.getName());
    private final CaseStudyService caseStudyService;

    public CaseStudyController(CaseStudyService caseStudyService) {
        this.caseStudyService = caseStudyService;
    }

    @PostMapping(value = "/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> saveCaseStudy(@RequestBody @Validated CaseStudy caseStudy, BindingResult errors) {
        LOGGER.debug("Saving new case study {}", caseStudy);
        if (errors.hasErrors()) {
            throw new NotFoundException(errors.toString());
        }
        CaseStudy returnedCaseStudy = caseStudyService.save(caseStudy);
        return ResponseEntity.ok().body(returnedCaseStudy);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public CaseStudy get(@PathVariable UUID id) {
        LOGGER.debug("Requesting case study with ID {}", id);
        return caseStudyService.get(id);
    }

    @GetMapping(produces = "application/json")
    public List<CaseStudy> getAll() {
        LOGGER.debug("Requesting all case studies");
        return caseStudyService.getAll();
    }

    @GetMapping(value = "/overviews", produces = "application/json")
    public List<CaseStudyOverview> getAllOverviews(@RequestParam(required = false) Boolean spotlit) {
        spotlit = spotlit != null && spotlit;
        LOGGER.debug(spotlit ? "Requesting all spotlit case study overviews" : "Requesting all case study overviews");
        return spotlit ? caseStudyService.getSpotlitOverviews() : caseStudyService.getAllOverviews();
    }

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public CaseStudy update(@PathVariable UUID id, @RequestBody CaseStudy newCaseStudy) {
        try {
            LOGGER.debug("Updating case study with ID {} with new case study {}", id, newCaseStudy);
            return caseStudyService.update(id, newCaseStudy);
        } catch (Exception e) {
            throw new RuntimeException("There was an error in the CaseStudyController - could not update case study", e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        try {
            LOGGER.debug("Deleting caseStudy with ID {}", id);
            caseStudyService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException("There was an error in the CaseStudyController - could not delete case study with ID " + id);
        }
    }
}
