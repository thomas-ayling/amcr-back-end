package com.globallogic.amcr.attachment_attempt_two.model.attachment_attempt_two;

import java.util.Arrays;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BinaryObject that)) return false;
        return Arrays.equals(getMedia(), that.getMedia());
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(getMedia());
    }

    @Override
    public String toString() {
        return "BinaryObject{" +
                "media=" + Arrays.toString(media) +
                '}';
    }
}