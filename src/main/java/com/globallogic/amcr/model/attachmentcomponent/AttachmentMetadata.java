package com.globallogic.amcr.model.attachmentcomponent;

import java.util.UUID;

public class AttachmentMetadata extends AttachmentResponse {
    private long crc;

    public AttachmentMetadata(UUID id, String name, long size, String type, long crc) {
        super(id, name, size, type);
        this.crc = crc;
    }

    public AttachmentMetadata() {
        super();
    }

    public long getCrc() {
        return crc;
    }

    public void setCrc(long crc) {
        this.crc = crc;
    }
}
