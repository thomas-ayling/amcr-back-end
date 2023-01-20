package com.globallogic.amcr.persistence.model.layoutcomponent;

import java.util.UUID;

public class Page {

    private UUID id;
    private String name;

    public Page(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public Page() {
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

    @Override
    public String toString() {
        return "Page{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
