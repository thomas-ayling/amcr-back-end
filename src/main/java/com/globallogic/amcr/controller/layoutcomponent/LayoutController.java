package com.globallogic.amcr.controller.layoutcomponent;

import com.globallogic.amcr.controller.librarycomponent.BookController;
import com.globallogic.amcr.model.Layout;
import com.globallogic.amcr.service.layoutcomponent.LayoutService;
import com.globallogic.amcr.utils.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/page-layout")
@CrossOrigin
public class LayoutController {

    private final Logger LOG = LoggerFactory.getLogger(LayoutController.class.getName());
    private final LayoutService layoutService;


    public LayoutController(LayoutService layoutService) {
        this.layoutService = layoutService;
    }

    @PostMapping(value = "/", consumes = "application/json")
    public Layout uploadLayout(@RequestBody Layout layout) {
        Assert.assertNotNull(layout, "Layout cannot be null");
        LOG.debug("Controller saving layout");
        return layoutService.save(layout);
    }

    @GetMapping(value = "/{id}", produces = "application/json")

    public Layout get(@PathVariable UUID id) {
        Assert.assertNotNull(id, "Id cannot be null");
        LOG.debug("Controller getting layout with id {}", id);
        return layoutService.get(id);
    }

    @GetMapping(produces = "application/json")
    public Layout getPage(@RequestParam String name) {
        Assert.assertNotNull(name, "Name cannot be null");
        LOG.debug("Controller getting layout with the name {}", name);
        return layoutService.getPage(name);
    }

    @GetMapping(value = "/", produces = "application/json")
    public List<Layout> getALl() {
        LOG.debug("Controller getting all layouts");
        return layoutService.getAll();
    }


    @PutMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    public Layout update(@PathVariable UUID id, @RequestBody Layout newLayout) {
        Assert.assertNotNull(id, "Id cannot be null");
        LOG.debug("Controller updating layout with id {}", id);
        try {
            return layoutService.update(id, newLayout);
        } catch (Exception e) {
            throw new RuntimeException("There was an error in the layoutController - could not update layout", e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable UUID id) {
        Assert.assertNotNull(id, "Id cannot be null");
        LOG.debug("Controller deleting layout with id {}", id);
        try {
            layoutService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException("There was an error in the layoutController - could not delete layout with ID " + id, e);

        }
    }
}
