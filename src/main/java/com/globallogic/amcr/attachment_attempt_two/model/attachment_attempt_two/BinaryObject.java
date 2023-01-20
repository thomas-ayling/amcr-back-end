package com.globallogic.amcr.attachment_attempt_two.model.attachment_attempt_two;

public class BinaryObject {

    private byte[] media;

    public BinaryObject() {
    }

    public byte[] getMedia() {
        return media;
    }

    public void setMedia(byte[] media) {
        this.media = media;
    }

    public BinaryObject(byte[] media) {
        this.media = media;
    }
}