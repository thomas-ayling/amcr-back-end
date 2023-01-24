package com.globallogic.amcr.model.attachmentcomponent;

import java.util.Map;
import java.util.UUID;

public class AttachmentMetadata extends AttachmentResponse {
    private long crc;
    private Map<String, Object> metadata;
    private String downloadUri;


    public AttachmentMetadata(UUID id, String name, long size, String type, long crc, Map<String, Object> metadata, String downloadUri) {
        super(id, name, size, type);
        this.crc = crc;
        this.metadata = metadata;
        this.downloadUri = downloadUri;
    }

//    public AttachmentMetadata(UUID id, long crc, Map<String, Object> metadata, String downloadUri) {
//        this.crc = crc;
//        this.metadata = metadata;
//        this.downloadUri = downloadUri;
//    }

    public AttachmentMetadata() {
        super();
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
