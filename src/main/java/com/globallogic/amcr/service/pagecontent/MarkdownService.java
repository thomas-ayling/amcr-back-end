package com.globallogic.amcr.service.pagecontent;

import com.globallogic.amcr.mapper.pagecontent.MarkdownMapper;
import com.globallogic.amcr.model.pagecontent.Markdown;
import com.globallogic.amcr.payload.FeedbackResponse;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MarkdownService {

    public final MarkdownMapper markdownMapper;

    public MarkdownService(MarkdownMapper markdownMapper) {
        this.markdownMapper = markdownMapper;
    }

    public ResponseEntity<Resource> saveMarkdown(Markdown markdown) {
        try {
            markdownMapper.save(markdown);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Markdown getByIdMarkdown(UUID id) { return markdownMapper.getById(id);}
    public List<Markdown> getAllMarkdown() {
        return markdownMapper.getAll();
    }

    public Markdown getLatestMarkdown() { return markdownMapper.getLatest();}

    public ResponseEntity<Resource> updateMarkdown(Markdown markdown) {
        try {
            markdownMapper.update(markdown);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Resource> deleteMarkdown(UUID id) {
        try {
            markdownMapper.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
