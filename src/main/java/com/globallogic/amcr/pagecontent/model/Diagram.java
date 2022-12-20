package com.globallogic.amcr.pagecontent.model;

public class Diagram {

    private int id;

    private int nodeId;

    private String title;

    private String body;

    public Diagram() {
    }

    public Diagram(int id, int nodeId, String title, String body) {
        this.id = id;
        this.nodeId = nodeId;
        this.title = title;
        this.body = body;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
