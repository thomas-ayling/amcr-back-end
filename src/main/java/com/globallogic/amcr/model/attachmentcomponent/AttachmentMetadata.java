package com.globallogic.amcr.model.attachmentcomponent;

import java.util.Map;
import java.util.Objects;

public class AttachmentMetadata {
    private String name;
    private String downloadUri;
    private long size;
    private long crc;
    private Map<Object,Object> metadata;

    public AttachmentMetadata(String name, String downloadUri, long size, long crc, Map<Object,Object> metadata) {
        this.name = name;
        this.downloadUri = downloadUri;
        this.size = size;
        this.crc = crc;
        this.metadata = metadata;
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

    public Map<Object,Object> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<Object,Object> metadata) {
        this.metadata = metadata;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AttachmentMetadata that = (AttachmentMetadata) o;
        return crc == that.crc && Objects.equals(name, that.name) && Objects.equals(downloadUri, that.downloadUri) && Objects.equals(size, that.size) && Objects.equals(metadata, that.metadata);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, downloadUri, size, crc, metadata);
    }

    @Override
    public String toString() {
        return "AttachmentMetadata{" +
                "name='" + name + '\'' +
                ", downloadUri='" + downloadUri + '\'' +
                ", size='" + size + '\'' +
                ", crc=" + crc +
                ", metadata=" + metadata +
                '}';
    }
}
