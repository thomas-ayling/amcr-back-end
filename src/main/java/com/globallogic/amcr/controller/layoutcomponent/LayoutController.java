package com.globallogic.amcr.controller.layoutcomponent;

import com.globallogic.amcr.persistence.model.layoutcomponent.Layout;
import com.globallogic.amcr.service.layoutcomponent.LayoutService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    @GetMapping("/get/{page}")
    public List<Layout> getLayoutByPage(@PathVariable String page) {
        return layoutService.getByPage(page);
    }

//    @GetMapping("/get/{profileId}")
//    public List<Layout> getLayoutByProfileId(@PathVariable UUID profileId) {
//        return layoutService.getLayoutByProfileId(profileId);
//    }

    @GetMapping("/{elementName}")
    public Layout getLayoutByName(@PathVariable String elementName) {
        return layoutService.getElementByName(elementName);
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
//    @PutMapping("/{profileId}")
//    public Layout updateIsMovable(@PathVariable UUID profileId, @RequestBody Layout newLayout) {
//        try {
//            return layoutService.update(profileId, newLayout);
//        } catch (Exception e) {
//            throw new RuntimeException("There was an error in the layoutController - could not update layout", e);
//        }
//    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable UUID id) {
        try {

            layoutService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException("There was an error in the LayoutController - could not delete layout with ID " + id);
        }
    }
}
