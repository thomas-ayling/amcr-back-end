package com.globallogic.amcr.attachment_component.persistance.model;

import javax.imageio.metadata.IIOMetadata;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

public class Attachment {
    private UUID id;
    private String name;
    private String downloadUri;
    private String contentType;
    private String size;
    private long crc;
    private String metadata;
    private byte[] data;

    public Attachment() {

    }

    public Attachment(UUID id, String name, String downloadUri, String contentType, String size, long crc, String metadata, byte[] data) {
        this.id = id;
        this.name = name;
        this.downloadUri = downloadUri;
        this.contentType = contentType;
        this.size = size;
        this.crc = crc;
        this.metadata = metadata;
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public long getCrc() {
        return crc;
    }

    public void setCrc(long crc) {
        this.crc = crc;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
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
        if (o == null || getClass() != o.getClass()) return false;
        Attachment that = (Attachment) o;
        return crc == that.crc && Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(downloadUri, that.downloadUri) && Objects.equals(contentType, that.contentType) && Objects.equals(size, that.size) && Objects.equals(metadata, that.metadata) && Arrays.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, name, downloadUri, contentType, size, crc, metadata);
        result = 31 * result + Arrays.hashCode(data);
        return result;
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
