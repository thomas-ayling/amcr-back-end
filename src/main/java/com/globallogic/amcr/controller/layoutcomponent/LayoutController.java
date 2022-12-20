package com.globallogic.amcr.controller.layoutcomponent;

import com.globallogic.amcr.persistence.model.layoutcomponent.Layout;
import com.globallogic.amcr.service.layoutcomponent.LayoutService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/page-layout")
@CrossOrigin(origins = "*")
public class LayoutController {


    private final LayoutService layoutService;

    public LayoutController(LayoutService layoutService) {
        this.layoutService = layoutService;
    }

    @PostMapping("/upload-layout")
    public ResponseEntity uploadLayout(@RequestBody Layout layout) {
        return layoutService.saveLayout(layout);
    }
    @GetMapping("/get-layout/{page}")
    public List<Layout> getLayoutByPage(@PathVariable String page) {
        return layoutService.getLayoutByPage(page);
    }

    @GetMapping("/get-all")
    public List<Layout> getALl() {
        return layoutService.getAll();
    }
}
