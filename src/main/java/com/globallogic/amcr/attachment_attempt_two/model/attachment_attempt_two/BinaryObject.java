package com.globallogic.amcr.attachment_attempt_two.model.attachment_attempt_two;

import java.util.UUID;

public class BinaryObject {
    private UUID id;
    private byte[] media;

    public BinaryObject(UUID id, byte[] media) {
        this.id = id;
        this.media = media;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public byte[] getMedia() {
        return media;
    }

    public void setMedia(byte[] media) {
        this.media = media;
    }
}


