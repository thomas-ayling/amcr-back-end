package com.globallogic.amcr.contactcomponent.service;

import com.globallogic.amcr.contactcomponent.persistence.dao.FileDao;
import com.globallogic.amcr.contactcomponent.persistence.payload.AttachmentMetadata;
import com.globallogic.amcr.contactcomponent.persistence.payload.AttachmentResponse;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    private final FileDao fileDao;

    public FileServiceImpl(FileDao fileDao) {
        this.fileDao = fileDao;
    }

    public ResponseEntity save(MultipartFile attachment, UUID feedbackId) {
        try {
            fileDao.save(attachment, feedbackId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Resource> get(UUID id) {
        try {
            AttachmentResponse attachmentResponse = fileDao.get(id);
            return ResponseEntity.ok().contentType(MediaType.parseMediaType(attachmentResponse.getFileType())).header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + attachmentResponse.getFileName() + "\"").body(new ByteArrayResource(attachmentResponse.getData()));
        }
        catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Transactional(readOnly = true)
    public AttachmentMetadata getAttachmentMetadata(UUID feedbackId) {
        return fileDao.getAttachmentMetadata(feedbackId);
    }
}
