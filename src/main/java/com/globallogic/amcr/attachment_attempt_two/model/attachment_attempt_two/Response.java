package com.globallogic.amcr.attachment_attempt_two.model.attachment_attempt_two;

import java.util.Arrays;
import java.util.Map;
import java.util.UUID;

public class Response extends Metadata {

    private byte[] media;

    public Response(UUID id, String name, long size, String type, long crc, Map<String, Object> metadata, UUID mediaId, byte[] media, String downloadUri) {
        super(id, name, size, type, crc, metadata, mediaId, downloadUri);
        this.media = media;
    }

    public byte[] getMedia() {
        return media;
    }

    public void setMedia(byte[] media) {
        this.media = media;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Response response)) return false;
        if (!super.equals(o)) return false;
        return Arrays.equals(getMedia(), response.getMedia());
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Arrays.hashCode(getMedia());
        return result;
    }

    @Override
    public String toString() {
        return "Response{" +
                "media=" + Arrays.toString(media) +
                '}';
    }
}