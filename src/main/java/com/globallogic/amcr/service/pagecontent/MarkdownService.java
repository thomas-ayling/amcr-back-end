package com.globallogic.amcr.service.pagecontent;

import com.globallogic.amcr.persistence.model.pagecontent.Markdown;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface MarkdownService {

    /**
     * @param markdown the markdown object with the data to be saved
     */
    void save(Markdown markdown);

    /**
     * @param id the id of the markdown object that will be returned
     * @return the markdown object with the specified id
     */
    Markdown get(UUID id);

    /**
     * @return a list of all markdown objects in the table
     */
    List<Markdown> getAll();

    /**
     * @return the latest markdown object that was entered in the markdown table
     */
    Markdown getLatest();

    /**
     * @param name the name of the markdown object that will be returned
     * @return the markdown object with the specified name
     */
    Markdown getByName(String name);

    /**
     * @param markdown the markdown object with the data to be updated
     * @param orderNumber the allocated number of the markdown object to be updated
     */
    void update(Markdown markdown, int orderNumber);

    /**
     * @param orderNumber the allocated number of the markdown object to be deleted
     */
    void delete (int orderNumber);
}
