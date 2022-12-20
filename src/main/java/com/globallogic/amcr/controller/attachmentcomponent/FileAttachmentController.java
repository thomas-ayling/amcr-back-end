package com.globallogic.amcr.controller.attachmentcomponent;

import com.globallogic.amcr.persistence.model.attachmentcomponent.CRC32C;
import com.globallogic.amcr.persistence.model.attachmentcomponent.UploadFileResponse;
import com.globallogic.amcr.service.attachmentcomponent.FileStorageService;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.io.IOException;
import java.util.UUID;

@RestController
public class FileAttachmentController {

    private static final Logger logger = LoggerFactory.getLogger(FileAttachmentController.class);

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping("/upload-file")
    public UploadFileResponse uploadFile(@RequestParam("file")MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);

        UUID mediaId = UUID.randomUUID();

        CRC32C crc = new CRC32C(); // IDK

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download-file/")
                .path(fileName)
                .toUriString();

        return new UploadFileResponse(mediaId ,fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }

//    A way to upload multiple files at once, removed as no longer needed
//    @PostMapping("/uploadMultipleFiles")
//    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
//        return Arrays.asList(files)
//                .stream()
//                .map(file -> uploadFile(file))
//                .collect(Collectors.toList());
//    }

    @GetMapping("/download-file/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Attempt to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Couldn't determine the type of the file.");
        }

        // Fallback to the default content type if the type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
