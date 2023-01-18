package com.globallogic.amcr.attachment_attempt_two.model.attachment_attempt_two;

import java.util.Map;
import java.util.UUID;

public class Metadata {
    private UUID id;
    private String name;
    private long size;
    private String type;
    private long crc;
    private Map<String, Object> metadata;
    private UUID mediaId;

    public Metadata(UUID id, String name, long size, String type, long crc, Map<String, Object> metadata, UUID mediaId) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.type = type;
        this.crc = crc;
        this.metadata = metadata;
        this.mediaId = mediaId;
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

    public UUID getMediaId() {
        return mediaId;
    }

    public void setMediaId(UUID mediaId) {
        this.mediaId = mediaId;
    }
}
