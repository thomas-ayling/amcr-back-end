package com.globallogic.amcr.controller.casestudies;

import com.globallogic.amcr.persistence.model.casestudies.CaseStudy;
import com.globallogic.amcr.persistence.payload.casestudies.CaseStudyOverview;
import com.globallogic.amcr.service.casestudies.CaseStudyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity saveCaseStudy(@RequestBody CaseStudy caseStudy) {
        try {
            CaseStudy returnedCaseStudy = caseStudyService.save(caseStudy);
            return ResponseEntity.ok().body(returnedCaseStudy);
        } catch (Exception e) {
            throw new RuntimeException("There was an error in the CaseStudyController - could not save case study", e);
        }
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
    public ResponseEntity delete(@PathVariable UUID id) {
        try {
            caseStudyService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException("There was an error in the CaseStudyController - could not delete case study with ID " + id);
        }
    }
}
