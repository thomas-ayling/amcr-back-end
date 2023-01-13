package com.globallogic.amcr.persistence.model.contactcomponent;

import com.globallogic.amcr.persistence.payload.contactcomponent.AttachmentResponse;
import com.globallogic.amcr.utils.Assert;

import java.util.Objects;
import java.util.UUID;

/**
 * Attachment model for sending attachments to the database
 */

public class Attachment extends AttachmentResponse {
    private UUID id;
    private long fileSize;
    private String downloadUri;
    private UUID feedbackId;

    /**
     * @param fileName the name of the file being saved
     * @param fileType the type of the file to be downloaded - received from the MultipartFile
     * @param fileSize the size of the file in bytes - received form the MultipartFile
     * @param data     the raw binary data of the file
     */

    public Attachment(String fileName, String fileType, long fileSize, byte[] data) {
        super(fileName, fileType, data);
        this.fileSize = Assert.assertNull(fileSize, "Data cannot be null");
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = Assert.assertNull(id, "ID cannot be null");

    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = Assert.assertNull(fileSize, "File size cannot be null");
    }

    public String getDownloadUri() {
        return downloadUri;
    }

    public void setDownloadUri(String downloadUri) {
        this.downloadUri = Assert.assertNull(downloadUri, "Download uri cannot be null");
    }

    public UUID getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(UUID feedbackId) {
        this.feedbackId = Assert.assertNull(feedbackId, "Feedback id cannot be null");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attachment that = (Attachment) o;
        return getFileSize() == that.getFileSize() && getId().equals(that.getId()) && getDownloadUri().equals(that.getDownloadUri()) && getFeedbackId().equals(that.getFeedbackId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFileSize(), getDownloadUri(), getFeedbackId());
    }

    @Override
    public String toString() {
        return "Attachment{" +
                "id=" + id +
                ", fileSize=" + fileSize +
                ", downloadUri='" + downloadUri + '\'' +
                ", feedbackId=" + feedbackId +
                '}' + super.toString();
    }
}
