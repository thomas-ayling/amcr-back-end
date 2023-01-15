package com.globallogic.amcr.persistence.payload.attachmentcomponent;

import java.util.Arrays;
import java.util.Objects;

public class AttachmentResponse {
    private String name;
    private String contentType;
    private byte[] data;

    public AttachmentResponse(String name, String contentType, byte[] data) {
        this.name = name;
        this.contentType = contentType;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
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
        AttachmentResponse that = (AttachmentResponse) o;
        return Objects.equals(name, that.name) && Objects.equals(contentType, that.contentType) && Arrays.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name, contentType);
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }

    @Override
    public String toString() {
        return "AttachmentResponse{" +
                "name='" + name + '\'' +
                ", contentType='" + contentType + '\'' +
                ", data=" + Arrays.toString(data) +
                '}';
    }
}
