package com.globallogic.amcr.model.attachmentcomponent;

import java.util.UUID;

public class Attachment extends AttachmentMetadata {
    private byte[] content;

    public Attachment(UUID id, String name, long size, String type, long crc, byte[] content) {
        super(id, name, size, type, crc);
        if (content != null && content.length != size) {
            throw new IllegalArgumentException("Length of the content array does not match the size");
        }
        this.content = content;
    }

    public Attachment() {
        super();
    }

    public static Attachment from(Attachment attachment, byte[] content) {
        Attachment newAttachment = new Attachment();
        newAttachment.setId(attachment.getId());
        newAttachment.setName(attachment.getName());
        newAttachment.setSize(attachment.getSize());
        newAttachment.setType(attachment.getType());
        newAttachment.setCrc(attachment.getCrc());
        newAttachment.content = content;
        return newAttachment;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}