package com.globallogic.amcr.persistence.payload.attachmentcomponent;

import javax.imageio.metadata.IIOMetadata;
import java.awt.Dimension;
import java.util.Map;

public class AttachmentMetadata {
    private String name;
    private String downloadUri;
    private String size;
    private long crc;
    private Map<Object,Object> metadata;

    public AttachmentMetadata(String name, String downloadUri, String size, long crc, Map<Object,Object> metadata) {
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

    public Map<Object,Object> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<Object,Object> metadata) {
        this.metadata = metadata;
    }
}
