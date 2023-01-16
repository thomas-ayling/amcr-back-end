package com.globallogic.amcr.persistence.model.contactcomponent;

import com.globallogic.amcr.utils.Assert;

import java.util.Objects;
import java.util.UUID;

/**
 * FeedbackAttachment model for sending attachments to the database
 */
@SuppressWarnings("unused")
public class FeedbackAttachment extends FeedbackAttachmentResponse {
    private UUID id;
    private long attachmentSize;
    private String downloadUri;
    private UUID feedbackId;

    /**
     * @param attachmentName the name of the attachment being saved
     * @param attachmentType the type of the attachment to be downloaded - received from the MultipartFile
     * @param attachmentSize the size of the attachment in bytes - received form the MultipartFile
     * @param data           the raw binary data of the attachment
     */

    public FeedbackAttachment(String attachmentName, String attachmentType, long attachmentSize, byte[] data) {
        super(attachmentName, attachmentType, data);
        setAttachmentSize(attachmentSize);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = Assert.assertNull(id, "ID cannot be null");
    }

    public long getAttachmentSize() {
        return attachmentSize;
    }

    public void setAttachmentSize(long attachmentSize) {
        this.attachmentSize = Assert.assertNull(attachmentSize, "File size cannot be null");
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
        FeedbackAttachment that = (FeedbackAttachment) o;
        return getAttachmentSize() == that.getAttachmentSize() && getId().equals(that.getId()) && getDownloadUri().equals(that.getDownloadUri()) && getFeedbackId().equals(that.getFeedbackId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAttachmentSize(), getDownloadUri(), getFeedbackId());
    }

    @Override
    public String toString() {
        return "FeedbackAttachment{" +
                "id=" + id +
                ", attachmentSize=" + attachmentSize +
                ", downloadUri='" + downloadUri + '\'' +
                ", feedbackId=" + feedbackId +
                '}' + super.toString();
    }
}
