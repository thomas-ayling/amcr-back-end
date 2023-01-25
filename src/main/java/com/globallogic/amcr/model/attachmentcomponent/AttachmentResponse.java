package com.globallogic.amcr.model.attachmentcomponent;

import java.util.UUID;

public class AttachmentResponse {
    private UUID id;
    private String name;
    private long size;
    private String type;

    public AttachmentResponse(UUID id, String name, long size, String type) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.type = type;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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