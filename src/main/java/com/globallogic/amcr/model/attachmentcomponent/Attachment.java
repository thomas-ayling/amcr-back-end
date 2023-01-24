package com.globallogic.amcr.model.attachmentcomponent;

import java.util.Map;
import java.util.UUID;

public class Attachment extends AttachmentMetadata {
    private byte[] content;

    public Attachment(UUID id, String name, long size, String type, UUID id1, long crc, Map<String, Object> metadata, String downloadUri, byte[] content) {
        super(id, name, size, type, id1, crc, metadata, downloadUri);
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
        newAttachment.setMetadata(attachment.getMetadata());
        newAttachment.setDownloadUri(attachment.getDownloadUri());
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