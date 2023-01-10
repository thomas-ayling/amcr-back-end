package com.globallogic.amcr.controller.casestudies;

import com.globallogic.amcr.exception.NotFoundException;
import com.globallogic.amcr.persistence.model.casestudies.CaseStudy;
import com.globallogic.amcr.persistence.model.casestudies.CaseStudyOverview;
import com.globallogic.amcr.service.casestudies.CaseStudyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/case-study")
@CrossOrigin(origins = "*")
public class CaseStudyController {
    private final CaseStudyService caseStudyService;

    public CaseStudyController(CaseStudyService caseStudyService) {
        this.caseStudyService = caseStudyService;
    }

    @PostMapping("/")
    public ResponseEntity<?> saveCaseStudy(@RequestBody @Validated CaseStudy caseStudy, BindingResult errors) {
            if (errors.hasErrors()) {
                throw new NotFoundException(errors.toString());
            }
            CaseStudy returnedCaseStudy = caseStudyService.save(caseStudy);
            return ResponseEntity.ok().body(returnedCaseStudy);
    }

    @GetMapping("/{id}")
    public CaseStudy get(@PathVariable UUID id) {
        return caseStudyService.get(id);
    }

    @GetMapping()
    public List<CaseStudy> getAll() {
        return caseStudyService.getAll();
    }

    @GetMapping("/overviews")
    public List<CaseStudyOverview> getAllOverviews(@RequestParam(required = false) Boolean spotlit) {
        spotlit = spotlit != null && spotlit;
        return spotlit ? caseStudyService.getSpotlitOverviews() : caseStudyService.getAllOverviews();
    }

    @PutMapping("/{id}")
    public CaseStudy update(@PathVariable UUID id, @RequestBody CaseStudy newCaseStudy) {
        try {
            return caseStudyService.update(id, newCaseStudy);
        } catch (Exception e) {
            throw new RuntimeException("There was an error in the CaseStudyController - could not update case study", e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        try {
            caseStudyService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException("There was an error in the CaseStudyController - could not delete case study with ID " + id);
        }
    }
}
