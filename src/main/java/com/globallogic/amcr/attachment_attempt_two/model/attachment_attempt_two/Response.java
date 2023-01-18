package com.globallogic.amcr.attachment_attempt_two.model.attachment_attempt_two;

import java.util.Map;
import java.util.UUID;

public class Response extends Metadata {

    byte[] media;

    public Response(UUID id, String name, long size, String type, long crc, Map<String, Object> metadata, UUID mediaId, byte[] media) {
        super(id, name, size, type, crc, metadata, mediaId);
        this.media = media;
    }

    public byte[] getMedia() {
        return media;
    }

    public void setMedia(byte[] media) {
        this.media = media;
    }
}
