package com.globallogic.amcr.persistence.model.pagecontent;

import org.springframework.util.Assert;

import java.util.Objects;
import java.util.UUID;

/**
 * Diagram model for Diagram component
 */
public class Diagram {

    private UUID id;

    private int nodeId;

    private String title;

    private String body;

    /**
     * @param nodeId the text input that is converted into markdown
     * @param title the text input that is converted into markdown
     * @param body the text input that is converted into markdown
     */

    public Diagram(UUID id, int nodeId, String title, String body) {
        this.id = id;
        this.nodeId = nodeId;
        this.title = title;
        this.body = body;
    }

    public Diagram() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        Assert.notNull(id, "ID is null");
        this.id = id;
    }

    public int getNodeId() {
        return nodeId;
    }

    public void setNodeId(int nodeId) {
        Assert.notNull(nodeId, "Node ID is null");
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Diagram diagram = (Diagram) o;
        return nodeId == diagram.nodeId && Objects.equals(id, diagram.id) && Objects.equals(title, diagram.title) && Objects.equals(body, diagram.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nodeId, title, body);
    }

    @Override
    public String toString() {
        return "Diagram{" +
                "id=" + id +
                ", nodeId=" + nodeId +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
