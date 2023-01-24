package com.globallogic.amcr.model.attachmentcomponent;

import java.util.Map;
import java.util.UUID;

public class AttachmentMetadata extends AttachmentResponse {
    private UUID id;
    private long crc;
    private Map<String, Object> metadata;
    private String downloadUri;

    public AttachmentMetadata(String name, long size, String type, UUID id, long crc, Map<String, Object> metadata, String downloadUri) {
        super(name, size, type);
        this.id = id;
        this.crc = crc;
        this.metadata = metadata;
        this.downloadUri = downloadUri;
    }

    public AttachmentMetadata(UUID id, String name, long size, String type, long crc, Map<String, Object> metadata, String downloadUri) {
        super(name, size, type);
        this.id = id;
        this.crc = crc;
        this.metadata = metadata;
        this.downloadUri = downloadUri;
    }

    public AttachmentMetadata(UUID id, long crc, Map<String, Object> metadata, String downloadUri) {
        this.id = id;
        this.crc = crc;
        this.metadata = metadata;
        this.downloadUri = downloadUri;
    }

    public AttachmentMetadata() {
        super();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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
}
