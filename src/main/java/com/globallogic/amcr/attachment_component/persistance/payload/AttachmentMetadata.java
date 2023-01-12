package com.globallogic.amcr.attachment_component.persistance.payload;

import javax.imageio.metadata.IIOMetadata;
import java.awt.Dimension;

public class AttachmentMetadata {
    private String name;
    private String downloadUri;
    private String size;
    private long crc;
    private String metadata;

    public AttachmentMetadata(String name, String downloadUri, String size, long crc, String metadata) {
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
}
