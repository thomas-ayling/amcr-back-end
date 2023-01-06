package com.globallogic.amcr.controller.pagecontent;

import com.globallogic.amcr.persistence.model.pagecontent.Markdown;
import com.globallogic.amcr.service.pagecontent.MarkdownServiceImpl;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * endpoint for markdown upload and download
 */
@RestController
@RequestMapping("/page-content/markdown")
@CrossOrigin(origins = "*")
public class MarkdownController {

    private final MarkdownServiceImpl markdownServiceImpl;

    public MarkdownController(MarkdownServiceImpl markdownServiceImpl) {
        this.markdownServiceImpl = markdownServiceImpl;
    }

    /**
     * @param markdown the markdown json object
     * @return returns a response entity either CREATED (201) or INTERNAL_SERVER_ERROR (500)
     */
    @PostMapping("/upload")
    public ResponseEntity saveMarkdown(@RequestBody Markdown markdown) {
        markdownServiceImpl.save(markdown);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * @param id the id of the markdown object to be displayed
     * @return returns the markdown data related to the given id
     */
    @GetMapping("/get-by-id/{id}")
    public Markdown getByIdMarkdown(@PathVariable UUID id) {
        return markdownServiceImpl.get(id);
    }

    /**
     * @return returns a list of all markdown data
     */
    @GetMapping("/get-all")
    public List<Markdown> getAllMarkdown() {
        return markdownServiceImpl.getAll();
    }

    /**
     * @return returns the latest markdown data uploaded to the database
     */
    @GetMapping("/get-latest")
    public Markdown getLatestMarkdown() {
        return markdownServiceImpl.getLatest();
    }

    /**
     * @param name the name of the markdown object to be displayed
     * @return returns the markdown data related to the given name
     */
    @GetMapping("/get-by-name/{name}")
    public Markdown getByNameMarkdown(@PathVariable String name) { return markdownServiceImpl.getByName(name); }

    /**
     * @param orderNumber the order number of the markdown entry to be updated
     * @param markdown the markdown object with the values that the database will be updated with
     * @return
     */
    @PutMapping("/update/{orderNumber}")
    public ResponseEntity updateMarkdown(@PathVariable int orderNumber, @RequestBody Markdown markdown) {
        markdownServiceImpl.update(markdown, orderNumber);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    /**
     * @param orderNumber the allocated number of the markdown entry to be deleted
     * @return returns a response entity, either OK (200) or INTERNAL_SERVER_ERROR (500)
     */
    @DeleteMapping("/delete/{orderNumber}")
    public ResponseEntity deleteMarkdown(@PathVariable int orderNumber) {
        markdownServiceImpl.delete(orderNumber);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
