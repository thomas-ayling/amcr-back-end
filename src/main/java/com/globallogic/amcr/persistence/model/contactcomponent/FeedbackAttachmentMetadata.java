package com.globallogic.amcr.persistence.model.contactcomponent;

import com.globallogic.amcr.utils.Assert;
import jakarta.validation.constraints.NotNull;

/**
 * File response for email attachment download link
 */
@SuppressWarnings("unused")
public class FeedbackAttachmentMetadata {
    @NotNull
    private String attachmentName;
    @NotNull
    private long attachmentSize;
    @NotNull
    private String downloadUri;

    /**
     * @param attachmentName    the name of the attachment
     * @param attachmentSize    the size of the attachment in bytes, converted to a readable format when added to the email
     * @param downloadUri the uri for the attachment download
     */

    public FeedbackAttachmentMetadata(String attachmentName, long attachmentSize, String downloadUri) {
        setAttachmentName(attachmentName);
        setAttachmentSize(attachmentSize);
        setDownloadUri(downloadUri);
    }

    public FeedbackAttachmentMetadata() {
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = Assert.assertNull(attachmentName, "File name cannot be null");
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
        this.downloadUri = Assert.assertNull(downloadUri, "Download URI cannot be null");
    }
}
