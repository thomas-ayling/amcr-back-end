package com.globallogic.amcr.controller.pagecontent;

import com.globallogic.amcr.model.pagecontent.Diagram;
import com.globallogic.amcr.model.pagecontent.Markdown;
import com.globallogic.amcr.payload.FeedbackResponse;
import com.globallogic.amcr.service.pagecontent.DiagramService;
import com.globallogic.amcr.service.pagecontent.MarkdownService;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/page-content/markdown")
@CrossOrigin(origins = "*")
public class MarkdownController {

    private final MarkdownService markdownService;

    public MarkdownController(MarkdownService markdownService, DiagramService diagramService) {
        this.markdownService = markdownService;
    }

    @PostMapping("/upload")
    public ResponseEntity uploadMarkdown(@RequestBody Markdown markdown) {
        UUID id = UUID.randomUUID();
        markdown.setId(id);
        return markdownService.saveMarkdown(markdown);
    }

    @GetMapping("/get-by-id/{id}")
    public Markdown getByIdMarkdown(@PathVariable UUID id) {
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
    public ResponseEntity updateMarkdown(@PathVariable UUID id, @RequestBody Markdown markdown) {
        return markdownService.updateMarkdown(markdown);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMarkdown(@PathVariable UUID id) {
        return markdownService.deleteMarkdown(id);
    }



}
