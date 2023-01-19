package com.globallogic.amcr.model.pagecontent;

import com.globallogic.amcr.utils.Assert;
import jakarta.validation.constraints.NotNull;

import java.util.*;

/**
 * Diagram model for Diagram component
 */
public class Diagram {

    private UUID id;

    @NotNull
    private List<Map<String,Object>> nodes;

    /**
     * @param nodes the nodes, with all their content: position, title and body content
     */

    public Diagram(UUID id, List<Map<String,Object>> nodes) {
        setId(id);
        setNodes(nodes);
    }

    public Diagram() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = Assert.assertNotNull(id,"ID cannot be null");
    }

    public List<Map<String,Object>> getNodes() {
        return nodes;
    }

    public void setNodes(List<Map<String,Object>> nodes) {
        this.nodes = Assert.assertNotNull(nodes,"Nodes data cannot be null");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Diagram diagram = (Diagram) o;
        return nodes.equals(diagram.nodes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodes);
    }

    @Override
    public String toString() {
        return "Diagram{" +
                "id=" + id +
                ", nodes=" + nodes +
                '}';
    }
}