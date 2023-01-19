package com.globallogic.amcr.model.pagecontent;

import com.globallogic.amcr.utils.Assert;

import java.util.Objects;
import java.util.UUID;

/**
 * Diagram model for Diagram component
 */
public class Diagram {

    private UUID id;

    private int nodePosition;

    private String title;

    private String body;

    /**
     * @param nodePosition the position in the diagram of each individual node
     * @param title the title text input of a diagram node
     * @param body the title text input of a diagram node
     */

    public Diagram(UUID id, int nodePosition, String title, String body) {
        setId(id);
        setNodePosition(nodePosition);
        setTitle(title);
        setBody(body);
    }

    public Diagram() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = Assert.assertNotNull(id, "ID cannot be null");
    }

    public int getNodePosition() {
        return nodePosition;
    }

    public void setNodePosition(int nodePosition) {
        this.nodePosition = nodePosition;
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
        return nodePosition == diagram.nodePosition && Objects.equals(title, diagram.title) && Objects.equals(body, diagram.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nodePosition, title, body);
    }

    @Override
    public String toString() {
        return "Diagram{" +
                "id=" + id +
                ", nodePosition=" + nodePosition +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}