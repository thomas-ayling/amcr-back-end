package com.globallogic.amcr.model.attachmentcomponent;

public class AttachmentResponse {
    private String name;
    private long size;
    private String type;

    public AttachmentResponse(String name, long size, String type) {
        this.name = name;
        this.size = size;
        this.type = type;
    }

    public AttachmentResponse() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
