package com.globallogic.amcr.persistence.dao.pagecontent;

import com.globallogic.amcr.mapper.pagecontent.MarkdownMapper;
import com.globallogic.amcr.persistence.dao.Dao;
import com.globallogic.amcr.persistence.model.pagecontent.Markdown;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class MarkdownDao implements Dao<Markdown, Markdown> {
    MarkdownMapper markdownMapper;
    public MarkdownDao (MarkdownMapper markdownMapper) {
        this.markdownMapper = markdownMapper;
    }

    /**
     * @param markdown the markdown object received from client, no ID set
     * @param markdownId the ID for the markdown object
     */
    @Override
    public void save(Markdown markdown, UUID markdownId) {
        try {
            markdown.setId(markdownId);
            markdownMapper.save(markdown);
        } catch (Exception e) {
            throw new RuntimeException("Could not save markdown data", e);
        }
    }
    /**
     * @param id the id of the markdown object to be retrieved from the database
     * @return returns the appropriate markdown entry
     */
    @Override
    public Markdown get(UUID id) {
        return markdownMapper.get(id);
    }
    /**
     * @return returns all markdown entries
     */
    @Override
    public List<Markdown> getAll() {return markdownMapper.getAll();}

    /**
     * @return returns the latest entry in the markdown table
     */
    public Markdown getLatest() {
        return markdownMapper.getLatest();
    }

    /**
     * @param name the name of the markdown input that was set by the user
     * @return returns the markdown entry with the specified name
     */
    public Markdown getByName(String name) { return markdownMapper.getByName(name); }

    /**
     * @param markdown the markdown object that will be updated
     * @param orderNumber the allocated number of the markdown object that will be updated
     */
    public void update(Markdown markdown, int orderNumber) {
        try {
            markdown.setOrderNumber(orderNumber);
            markdownMapper.update(markdown);
        } catch (Exception e){
            throw new RuntimeException("Could not update markdown data", e);
        }
    }

    /**
     * @param orderNumber the allocated number of the markdown object that will be deleted
     */
    public void delete(int orderNumber) {
        try {
            markdownMapper.delete(orderNumber);
        } catch (Exception e) {
            throw new RuntimeException("Could not delete markdown data", e);
        }
    }
}