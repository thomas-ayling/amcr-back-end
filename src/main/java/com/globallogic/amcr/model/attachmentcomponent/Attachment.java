package com.globallogic.amcr.model.attachmentcomponent;

import java.util.Map;
import java.util.UUID;

public class Attachment {
    private UUID id;
    private String name;
    private long size;
    private String type;
    private long crc;
    private Map<String, Object> metadata;
    private byte[] content;
    private String downloadUri;

    public Attachment() {
    }

    public Attachment(UUID id, String name, long size, String type, long crc, Map<String, Object> metadata, byte[] content, String downloadUri) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.type = type;
        this.crc = crc;
        this.metadata = metadata;
        this.content = content;
        this.downloadUri = downloadUri;
    }

    public void setAllButContent(Attachment attachment) {
        this.id = attachment.getId();
        this.name = attachment.getName();
        this.size = attachment.getSize();
        this.type = attachment.getType();
        this.crc = attachment.getCrc();
        this.metadata = attachment.getMetadata();
        this.downloadUri = attachment.getDownloadUri();
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

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }

    public String getDownloadUri() {
        return downloadUri;
    }

    public void setDownloadUri(String downloadUri) {
        this.downloadUri = downloadUri;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}