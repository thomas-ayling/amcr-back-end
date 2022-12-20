package com.globallogic.amcr.contactcomponent.controller;


import com.globallogic.amcr.contactcomponent.service.FileServiceImpl;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

/**
 * endpoint for file download
 */

@Controller
@RequestMapping("/file")
@CrossOrigin(origins = "*")
public class FileController {
    private final FileServiceImpl fileServiceImpl;

    public FileController(FileServiceImpl fileServiceImpl) {
        this.fileServiceImpl = fileServiceImpl;
    }

    /**
     * @param id the id of the file to be downloaded
     * @return returns a response entity with the relevant headers and the binary data to allow for easy download on the front end
     */

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> getAttachment(@PathVariable UUID id) {
        return fileServiceImpl.get(id);
    }

}
