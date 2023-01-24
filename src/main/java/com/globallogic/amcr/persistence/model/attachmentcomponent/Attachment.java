package com.globallogic.amcr.persistence.model.attachmentcomponent;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class Attachment {
    private UUID id;
    private String name;
    private String downloadUri;
    private String contentType;
    private long size;
    private long crc;
    private Map<String, Object> metadata;
    private byte[] data;

    public Attachment() {
    }

    public Attachment(UUID id, String name, String downloadUri, String contentType, long size, long crc, byte[] data) {
        this.id = id;
        this.name = name;
        this.downloadUri = downloadUri;
        this.contentType = contentType;
        this.size = size;
        this.crc = crc;
        this.data = data;
    }

    public Attachment(String name, String contentType, long size, long crc, Map<String, Object> metadata, byte[] data) {
        this.name = name;
        this.contentType = contentType;
        this.size = size;
        this.crc = crc;
        this.metadata = metadata;
        this.data = data;
    }

    public Attachment(String name, String contentType, long size, long crc, byte[] data) {
        this.name = name;
        this.contentType = contentType;
        this.size = size;
        this.crc = crc;
        this.data = data;
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

    public String getDownloadUri() {
        return downloadUri;
    }

    public void setDownloadUri(String downloadUri) {
        this.downloadUri = downloadUri;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
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

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Attachment that)) return false;
        return getCrc() == that.getCrc() && Objects.equals(getName(), that.getName()) && Objects.equals(getContentType(), that.getContentType()) && Objects.equals(getSize(), that.getSize());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getContentType(), getSize(), getCrc());
    }

    @Override
    public String toString() {
        return "Attachment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", downloadUri='" + downloadUri + '\'' +
                ", contentType='" + contentType + '\'' +
                ", size='" + size + '\'' +
                ", crc=" + crc +
                ", metadata='" + metadata + '\'' +
                ", data=" + Arrays.toString(data) +
                '}';
    }
}
