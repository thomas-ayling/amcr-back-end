package com.globallogic.amcr.model;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Layout {
    private UUID id;
    private String name;
    private List<Map<String, Object>> components;
    private boolean Static;


    public Layout(UUID id, String name, List<Map<String, Object>> components, boolean aStatic) {
        this.id = id;
        this.name = name;
        this.components = components;
        Static = aStatic;
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

    public Boolean getStatic() {
        return Static;
    }

    public void setStatic(Boolean Static) {
        this.Static = Static;
    }
}
