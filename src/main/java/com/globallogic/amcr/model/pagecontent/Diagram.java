package com.globallogic.amcr.model.pagecontent;

import java.util.UUID;

public class Diagram {

    private UUID id;

    private int nodeId;

    private String title;

    private String body;

    public Diagram() {
    }

    public Diagram(int nodeId, String title, String body) {
        this.nodeId = nodeId;
        this.title = title;
        this.body = body;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getNodeId() {
        return nodeId;
    }

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
