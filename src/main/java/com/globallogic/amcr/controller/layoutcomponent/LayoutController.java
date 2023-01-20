package com.globallogic.amcr.controller.layoutcomponent;

import com.globallogic.amcr.persistence.model.layoutcomponent.Layout;
import com.globallogic.amcr.service.layoutcomponent.LayoutService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/page-layout")
@CrossOrigin(origins = "*")
public class LayoutController {


    private final LayoutService layoutService;


    public LayoutController(LayoutService layoutService) {
        this.layoutService = layoutService;
    }

    @PostMapping("/")
    public ResponseEntity uploadLayout(@RequestBody Layout layout) {
        return layoutService.saveLayout(layout);
    }

    @GetMapping("/{id}")
    public Layout getById(@PathVariable UUID id) {
        return layoutService.getById(id);
    }

//    @GetMapping("/get/{profileId}")
//    public List<Layout> getLayoutByProfileId(@PathVariable UUID profileId) {
//        return layoutService.getLayoutByProfileId(profileId);
//    }

    @GetMapping("/elementname={elementName}")
    public Layout getLayoutByName(@PathVariable String elementName) {
        return layoutService.getElementByName(elementName);
    }

    @GetMapping(value = "/page={page}", produces = "application/json")
    public List<Layout> getLayoutByPage(@PathVariable String page) {
        return layoutService.getByPage(page);
    }

    @GetMapping("/")
    public List<Layout> getALl() {
        return layoutService.getAll();
    }

//    @PutMapping("/{id}")
//    public Layout update(@PathVariable UUID id, @RequestBody Layout newLayout) {
//        try {
//            return layoutService.update(id, newLayout);
//        } catch (Exception e) {
//            throw new RuntimeException("There was an error in the layoutController - could not update layout", e);
//        }
//    }
//    @PutMapping("/{id}")
//    public Layout update(@PathVariable UUID id, @RequestBody Layout newLayout) {
//        try {
//            return layoutService.update(id, newLayout);
//        } catch (Exception e) {
//            throw new RuntimeException("There was an error in the layoutController - could not update layout", e);
//        }
//    }

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity update(@PathVariable UUID id, @RequestBody @Validated Layout newLayout) {
        layoutService.update(id, newLayout);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

//    @PutMapping(value = "/page={page}", consumes = "application/json", produces = "application/json")
//    public ResponseEntity updateByPage(@PathVariable String page, @RequestBody @Validated Layout newLayout) {
//        layoutService.updateByPage(page, newLayout);
//        return new ResponseEntity<>(HttpStatus.ACCEPTED);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable UUID id) {
        try {

            layoutService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException("There was an error in the LayoutController - could not delete layout with ID " + id, e);
        }
    }
}
