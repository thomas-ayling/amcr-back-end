package com.globallogic.amcr.model.attachmentcomponent;

import java.util.UUID;

public class Attachment extends Content {
    private UUID id;
    private String name;
    private long size;
    private String type;
    private long crc;

    public Attachment() {}

    public Attachment(UUID id, String name, long size, String type, long crc, byte[] content) {
        super(content);
        this.id = id;
        this.name = name;
        this.size = size;
        this.type = type;
        this.crc = crc;
    }

    public Attachment(UUID id, String name, long size, String type, long crc) {
        super();
        this.id = id;
        this.name = name;
        this.size = size;
        this.type = type;
        this.crc = crc;
    }

    public static Attachment from(Attachment attachment, byte[] content) {
        Attachment newAttachment = new Attachment();
        newAttachment.setId(attachment.getId());
        newAttachment.setName(attachment.getName());
        newAttachment.setSize(attachment.getSize());
        newAttachment.setType(attachment.getType());
        newAttachment.setCrc(attachment.getCrc());
        newAttachment.setContent(content);
        return newAttachment;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getCrc() {
        return crc;
    }

    public void setCrc(long crc) {
        this.crc = crc;
    }
}