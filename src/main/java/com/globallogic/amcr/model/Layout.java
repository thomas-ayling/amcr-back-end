package com.globallogic.amcr.model;

import com.globallogic.amcr.utils.Assert;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Layout {
    private UUID id;
    private String name;
    private List<Map<String, Object>> components;


    public Layout(UUID id, String name, List<Map<String, Object>> components) {
        this.id = Assert.assertNotNull(id, "id must not be null");
        this.name = Assert.assertNotNull(name, "name must not be null");
        this.components = components;

    }

    public Layout() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Map<String, Object>> getComponents() {
        return components;
    }

    public void setComponents(List<Map<String, Object>> components) {
        this.components = components;
    }

}
