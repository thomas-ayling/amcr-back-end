package com.globallogic.amcr.controller.layoutcomponent;

import com.globallogic.amcr.model.Layout;
import com.globallogic.amcr.service.layoutcomponent.LayoutService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/page-layout")
@CrossOrigin
public class LayoutController {


    private final LayoutService layoutService;


    public LayoutController(LayoutService layoutService) {
        this.layoutService = layoutService;
    }

    @PostMapping("/")
    public Layout uploadLayout(@RequestBody Layout layout) {
        return layoutService.save(layout);
    }

    @GetMapping("/{id}")
    public Layout get(@PathVariable UUID id) {
        return layoutService.get(id);
    }

    @GetMapping("/name={name}")
    public Layout getPage(@PathVariable String name) {
        return layoutService.getPage(name);
    }

    @GetMapping("/")
    public List<Layout> getALl() {
        return layoutService.getAll();
    }


    @PutMapping("/{id}")
    public Layout update(@PathVariable UUID id, @RequestBody Layout newLayout) {
        try {
            return layoutService.update(id, newLayout);
        } catch (Exception e) {
            throw new RuntimeException("There was an error in the layoutController - could not update layout", e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable UUID id) {
        try {
            layoutService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException("There was an error in the layoutController - could not delete layout with ID " + id, e);

        }
    }
}
