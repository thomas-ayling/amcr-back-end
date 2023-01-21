package com.globallogic.amcr.model.attachmentcomponent;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class Metadata {
    private UUID id;
    private String name;
    private long size;
    private String type;
    private long crc;
    private Map<String, Object> metadata;
    private UUID mediaId;
    private String downloadUri;

    public Metadata() {
    }

    public Metadata(UUID id, String name, long size, String type, long crc, Map<String, Object> metadata, UUID mediaId, String downloadUri) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.type = type;
        this.crc = crc;
        this.metadata = metadata;
        this.mediaId = mediaId;
        this.downloadUri = downloadUri;
    }

    public String getDownloadUri() {
        return downloadUri;
    }

    public void setDownloadUri(String downloadUri) {
        this.downloadUri = downloadUri;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Metadata metadata1)) return false;
        return getSize() == metadata1.getSize() && getCrc() == metadata1.getCrc() && Objects.equals(getId(), metadata1.getId()) && Objects.equals(getName(), metadata1.getName()) && Objects.equals(getType(), metadata1.getType()) && Objects.equals(getMetadata(), metadata1.getMetadata()) && Objects.equals(getMediaId(), metadata1.getMediaId()) && Objects.equals(getDownloadUri(), metadata1.getDownloadUri());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getSize(), getType(), getCrc(), getMetadata(), getMediaId(), getDownloadUri());
    }

    @Override
    public String toString() {
        return "Metadata{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", size=" + size +
                ", type='" + type + '\'' +
                ", crc=" + crc +
                ", metadata=" + metadata +
                ", mediaId=" + mediaId +
                ", downloadUri='" + downloadUri + '\'' +
                '}';
    }
}