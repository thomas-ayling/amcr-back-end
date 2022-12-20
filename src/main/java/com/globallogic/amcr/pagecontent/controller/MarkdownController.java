package com.globallogic.amcr.pagecontent.controller;

import com.globallogic.amcr.pagecontent.persistence.model.Markdown;
import com.globallogic.amcr.pagecontent.service.MarkdownService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/page-content/markdown")
@CrossOrigin(origins = "*")
public class MarkdownController {

    private final MarkdownService markdownService;

    public MarkdownController(MarkdownService markdownService) {
        this.markdownService = markdownService;
    }

    @PostMapping("/upload")
    public ResponseEntity uploadMarkdown(@RequestBody Markdown markdown) {
        return markdownService.saveMarkdown(markdown);
    }

    @GetMapping("/get-by-id/{id}")
    public Markdown getByIdMarkdown(@PathVariable int id) {
        return markdownService.getByIdMarkdown(id);
    }

    @GetMapping("/get-all")
    public List<Markdown> getManyMarkdown() {
        return markdownService.getAllMarkdown();
    }

    @GetMapping("/get-latest")
    public Markdown getLatestMarkdown() {
        return markdownService.getLatestMarkdown();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateMarkdown(@PathVariable int id, @RequestBody Markdown markdown) {
        return markdownService.updateMarkdown(markdown);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMarkdown(@PathVariable int id) {
        return markdownService.deleteMarkdown(id);
    }
}
