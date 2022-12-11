package com.globallogic.amcr.service;

import com.globallogic.amcr.exception.FileStorageException;
import com.globallogic.amcr.mapper.FeedbackMapper;
import com.globallogic.amcr.mapper.FileMapper;
import com.globallogic.amcr.model.Attachment;
import com.globallogic.amcr.model.Feedback;
import com.globallogic.amcr.payload.AttachmentMetadata;
import com.globallogic.amcr.payload.AttachmentResponse;
import com.globallogic.amcr.payload.FeedbackResponse;
import com.globallogic.amcr.utils.ByteConverter;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class FeedbackService {

    private final FeedbackMapper feedbackMapper;
    private final FileMapper fileMapper;

    public FeedbackService(FeedbackMapper feedbackMapper, FileMapper fileMapper) {
        this.feedbackMapper = feedbackMapper;
        this.fileMapper = fileMapper;
    }

    private int saveAttachment(MultipartFile incomingAttachment, UUID feedbackId) {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(incomingAttachment.getOriginalFilename()));
        try {
            // Generate random UUID for the file
            UUID fileId = UUID.randomUUID();
            // Generate file download uri for the attachment
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/feedback/file-download/").path(fileId.toString()).toUriString();
            // Create new Attachment object with generated params
            Attachment attachment = new Attachment(fileId, fileName, incomingAttachment.getContentType(), ByteConverter.bytesToReadable(incomingAttachment.getSize()), incomingAttachment.getBytes(), fileDownloadUri, feedbackId);
            return fileMapper.save(attachment);
        } catch (IOException ioe) {
            if (fileName.contains("..")) {
                throw new FileStorageException("Filename contains invalid path sequence '..' " + fileName);
            }
            try {
                throw new FileStorageException(fileName + " could not be saved.");
            } catch (FileStorageException fse) {
                throw new RuntimeException();
            }

        }

    }

    public UUID save(Feedback feedback, MultipartFile attachment) {
        UUID feedbackId = UUID.randomUUID();
        feedback.setId(feedbackId);
        feedbackMapper.save(feedback);
        if (attachment != null) saveAttachment(attachment, feedbackId);
        return feedbackId;
    }

    public List<FeedbackResponse> getAll() {
        return feedbackMapper.getAll();
    }

    public List<FeedbackResponse> getLatest() { return feedbackMapper.getLatest(); }

    public List<FeedbackResponse> getOlder(Integer last) {
        return feedbackMapper.getOlder(last);
    }

    public ResponseEntity<Resource> getAttachment(UUID id) {
        AttachmentResponse attachmentResponse = fileMapper.getFile(id);
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(attachmentResponse.getFileType())).header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + attachmentResponse.getFileName() + "\"").body(new ByteArrayResource(attachmentResponse.getData()));
    }

    public AttachmentMetadata getAttachmentMetadata(UUID feedbackId) {
        return fileMapper.getFileMetadata(feedbackId);
    }
}
