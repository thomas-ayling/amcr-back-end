package com.globallogic.amcr.controller.sharedcomponents;

import com.globallogic.amcr.exception.NotFoundException;
import com.globallogic.amcr.model.sharedcomponents.MainCarousel;
import com.globallogic.amcr.service.sharedcomponents.MainCarouselService;
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
@RequestMapping("/main-carousel")
@CrossOrigin
public class MainCarouselController {

    private final Logger LOG = LoggerFactory.getLogger(MainCarouselController.class);

    private final MainCarouselService mainCarouselService;

    public MainCarouselController(MainCarouselService mainCarouselService) {
        this.mainCarouselService = mainCarouselService;
    }

    @PostMapping(value = "/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<MainCarousel> saveMainCarousel(@RequestBody @Validated MainCarousel mainCarousel, BindingResult errors) {
        if (errors.hasErrors()) {
            throw new NotFoundException(errors.toString());
        }
        LOG.debug("Controller saving new main carousel");
        MainCarousel newMainCarousel = mainCarouselService.save(mainCarousel);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newMainCarousel.getId()).toUri()).body(newMainCarousel);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<MainCarousel> get(@PathVariable UUID id) {
        LOG.debug("Controller requesting main carousel with ID {}", id);
        return ResponseEntity.ok().body(mainCarouselService.get(id));
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<MainCarousel>> getAll() {
        LOG.debug("Controller requesting all main carousels");
        return ResponseEntity.ok().body(mainCarouselService.getAll());
    }

    @GetMapping(value = "/{location}", produces = "application/json")
    public ResponseEntity<MainCarousel> getByLocation(@PathVariable String location) {
        LOG.debug("Controller requesting all Main Carousels by location {}", location);
        return ResponseEntity.ok().body(mainCarouselService.getByLocation(location));
    }

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<MainCarousel> update(@PathVariable UUID id, @RequestBody MainCarousel newMainCarousel) {
        LOG.debug("Controller updating main carousel with ID {}", id);
        return ResponseEntity.accepted().body(mainCarouselService.update(id, newMainCarousel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        LOG.debug("Controller requesting deletion of main carousel with ID {}", id);
        mainCarouselService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
