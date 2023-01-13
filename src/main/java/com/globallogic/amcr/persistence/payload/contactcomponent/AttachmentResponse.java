package com.globallogic.amcr.persistence.payload.contactcomponent;

import com.globallogic.amcr.utils.Assert;
import jakarta.validation.constraints.NotNull;

/**
 * FeedbackAttachment response to download an attachment from the attachments database
 */
@SuppressWarnings("unused")
public class AttachmentResponse {
    @NotNull
    private String attachmentName;
    @NotNull
    private String attachmentType;
    @NotNull
    private byte[] data;

    /**
     * @param attachmentName the name of the attachment
     * @param attachmentType the type of the attachment
     * @param data           the raw binary data of the attachment to be downloaded
     */

    public AttachmentResponse(String attachmentName, String attachmentType, byte[] data) {
        this.attachmentName = Assert.assertNull(attachmentName, "File name cannot be null");
        this.attachmentType = Assert.assertNull(attachmentType, "File type cannot be null");
        this.data = Assert.assertNull(data, "Data cannot be null");
    }

    public AttachmentResponse() {
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = Assert.assertNull(attachmentName, "File name cannot be null");
    }

    public String getAttachmentType() {
        return attachmentType;
    }

    public void setAttachmentType(String attachmentType) {
        this.attachmentType = Assert.assertNull(attachmentType, "File type cannot be null");
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = Assert.assertNull(data, "Data cannot be null");
    }
}
